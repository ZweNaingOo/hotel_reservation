package studentregistration.persistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import studentregistration.models.EditStudentDTO;
import studentregistration.models.StudentAllDetailsResponseDTO;
import studentregistration.models.StudentRequestDTO;
import studentregistration.models.StudentResponseDTO;
import studentregistration.utils.Result;

public class StudentRepository {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	public Result add(StudentRequestDTO student, int user_id) {
		Result result=new Result();
		int id=0;
		String sql="insert into student(name, dob, gender, education, user_id, photo, phone) values(?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, student.getName());
			ps.setString(2, student.getDob());
			ps.setInt(3, student.getGender());
			ps.setInt(4, student.getEducation());
			ps.setInt(5, user_id);
			ps.setBlob(6, student.getImage());
			ps.setString(7, student.getPhone());
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()) {
		    	  id=rs.getInt(1);
		      }else {
		          throw new SQLException("Creating payment failed, no ID obtained.");
		      }
			for(String courseId:student.getCourses()) {
				sql="insert into student_has_course(student_id, course_id) values(?, ?)";
				ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setInt(2, Integer.parseInt(courseId));
				result.setResult(ps.executeUpdate());
			}
			result.setMessage("Successfully add student");
		}catch(SQLException e){
			System.out.println("SQL add student error "+ e);
			return result;
		}
		return result;
	}
	public Result edit(EditStudentDTO student, int user_id) {
		Result result=new Result();
		String sql="";
		boolean hasImage=student.getImage()!=null;
		if(hasImage)
			sql="update student set name=?, dob=?, gender=?, education=?, user_id=?, photo=? where id=?";
		else
			sql="update student set name=?, dob=?, gender=?, education=?, user_id=? where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setString(2, student.getDob());
			ps.setInt(3, student.getGender());
			ps.setInt(4, student.getEducation());
			ps.setInt(5, user_id);
			System.out.println("image "+student.getImage());
			if(hasImage) {
				ps.setBlob(6, student.getImage());
				ps.setInt(7, student.getId());
			}else {
				ps.setInt(6, student.getId());
			}
			ps.executeUpdate();
			sql="delete from student_has_course where student_id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, student.getId());
			if(ps.executeUpdate()>0)
				for(String courseId:student.getCourses()) {
					sql="insert into student_has_course(student_id, course_id) values(?, ?)";
					ps=con.prepareStatement(sql);
					ps.setInt(1, student.getId());
					ps.setInt(2, Integer.parseInt(courseId));
					result.setResult(ps.executeUpdate());
				}
			result.setMessage("Successfully edit student");
		}catch(SQLException e){
			System.out.println("SQL edit student error "+ e);
			return result;
		}
		return result;
	}
	public List<StudentResponseDTO> findAll(){
		String sql="select s.id, s.name, GROUP_CONCAT(c.name SEPARATOR ', ') AS courses "
				+ "from student s "
				+ "inner join student_has_course sc on s.id = sc.student_id "
				+ "inner join course c on sc.course_id = c.id "
				+ "group by  s.id;";
		StudentResponseDTO student;
		List<StudentResponseDTO> students=new ArrayList<>();
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				student=new StudentResponseDTO();
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setCourses(rs.getString(3));
				students.add(student);
			}
		}catch(SQLException e) {
			System.out.println("Student find all error :"+e);
		}
		return students;
	}
	public StudentAllDetailsResponseDTO findAllDetails(int id){
		String sql="select s.*, GROUP_CONCAT(c.name SEPARATOR ', ') AS courses "
				+ "from student s "
				+ "inner join student_has_course sc on s.id = sc.student_id "
				+ "inner join course c on sc.course_id = c.id "
				+ "where s.id = "+id
				+ " group by s.id;";
		StudentAllDetailsResponseDTO student=new StudentAllDetailsResponseDTO();
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				student.setId(rs.getInt(1));
				student.setName(rs.getString(2));
				student.setDob(rs.getString(3));
				student.setGender(rs.getInt(4));
				student.setEducation(rs.getInt(5));
				student.setPhoto(Base64.getEncoder().encodeToString(rs.getBytes(6)));
				student.setDate(rs.getString(7));
				student.setUser_id(rs.getInt(8));
				student.setPhone(rs.getString(9));
				student.setCourses(rs.getString(10));
				
			}
		}catch(SQLException e) {
			System.out.println("Student find all details error :"+e);
		}
		return student;
	}
	public List<Integer> findCourses(int id){
		List<Integer>courseIds=new ArrayList<>();
		String sql="select course_id from student_has_course where student_id ="+id;
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				courseIds.add(rs.getInt(1));
			}
		}catch(SQLException e) {
			System.out.println("Student findCourses all details error :"+e);
		}
		return courseIds;
	}
	public void delete(int id) {
		String sql="delete from student_has_course where student_id = ?;";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			sql="delete from student where id=?;";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("SQL delete student error "+e);
		}
	}
}

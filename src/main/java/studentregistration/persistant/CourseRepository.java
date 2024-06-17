package studentregistration.persistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studentregistration.models.CourseResponseDTO;
import studentregistration.utils.Result;

public class CourseRepository {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public Result add(String course, int userId) {
		Result result=new Result();
		String sql="insert into course(name, user_id) values(?, ?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, course);
			ps.setInt(2, userId);
			result.setResult(ps.executeUpdate());
			result.setMessage("Successfully add course");
		}catch(SQLException e){
			System.out.println("SQL add course error "+ e);
//			if(e.toString().contains("name")) {
//				result.setMessage("This course is already added.");
//			}
			System.out.println("SQL error "+e.toString());
			return result;
		}
		return result;
	}
	public List<CourseResponseDTO> findAll(){
		String sql="select * from course where status!=0";
		CourseResponseDTO course;
		List<CourseResponseDTO> courses=new ArrayList<>();
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				course=new CourseResponseDTO();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setUser_id(rs.getInt("user_id"));
				course.setStatus(rs.getInt("status"));
				courses.add(course);
			}
		}catch(SQLException e) {
			System.out.println("Course find all error :"+e);
		}
		return courses;
	}
	public List<CourseResponseDTO> findAllForList(){
		String sql="select * from course";
		CourseResponseDTO course;
		List<CourseResponseDTO> courses=new ArrayList<>();
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				course=new CourseResponseDTO();
				course.setId(rs.getInt("id"));
				course.setName(rs.getString("name"));
				course.setUser_id(rs.getInt("user_id"));
				course.setStatus(rs.getInt("status"));
				courses.add(course);
			}
		}catch(SQLException e) {
			System.out.println("Course find all error :"+e);
		}
		return courses;
	}
	public Result disable(int id) {
		String sql="update course set status=0 where id=?";
		Result result=new Result();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			result.setResult(ps.executeUpdate());
			result.setMessage("Disabled course");
		}catch(SQLException e) {
			System.out.println("Disable course error : "+e);
		}
		return result;
	}
	public Result enable(int id) {
		String sql="update course set status=1 where id=?";
		Result result=new Result();
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			result.setResult(ps.executeUpdate());
			result.setMessage("Enabled course");
		}catch(SQLException e) {
			System.out.println("Enable course error : "+e);
		}
		return result;
	}
}

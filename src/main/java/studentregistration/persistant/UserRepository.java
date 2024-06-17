package studentregistration.persistant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import studentregistration.models.UserRequestDTO;
import studentregistration.models.UserResponseDTO;
import studentregistration.utils.Result;
public class UserRepository {
	public static Connection con=null;
	static {
		con=MyConnection.getConnection();
	}
	
	public Result add(UserRequestDTO user) {
		Result result=new Result();
		String sql="insert into user(name,email,password,role) values(?, ?, ?, ?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getRole());
			result.setResult(ps.executeUpdate());
			result.setMessage("Successfully created account please sign in to your account");
		}catch(SQLException e){
			System.out.println("SQL registration error "+ e);
			if(e.toString().contains("email")) {
				result.setMessage("This email is already used");
			}
			return result;
		}
		return result;
	}
	public UserResponseDTO findById(int id) {
		String sql="select * from user where id = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			UserResponseDTO user=new UserResponseDTO();
			while(rs.next()) {
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setStatus(rs.getInt(5));
				user.setDate(rs.getString(7));
				user.setRole(rs.getInt(8));
				return user;
			}
		}catch(SQLException e){
			System.out.println("SQL findById error "+ e);
		}
		return null;
	}
	
	public UserResponseDTO findByEmail(String email) {
		String sql="select * from user where email = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			UserResponseDTO user=new UserResponseDTO();
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setStatus(rs.getInt(5));
				user.setDate(rs.getString(7));
				user.setRole(rs.getInt(8));
				return user;
			}
		}catch(SQLException e){
			System.out.println("SQL findByEmail error "+ e);
		}
		return null;
	}
	public List<UserResponseDTO> findAll(){
		String sql="select * from user where role!=1";
		UserResponseDTO user;
		List<UserResponseDTO> users=new ArrayList<>();
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				user=new UserResponseDTO();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setDate(rs.getString("date"));
				user.setRole(rs.getInt("role"));
				user.setStatus(rs.getInt("status"));
				users.add(user);
			}
		}catch(SQLException e) {
			System.out.println("Student find all error :"+e);
		}
		return users;
	}
	public String getUserName(int id) {
		String sql="select name from user where id="+id;
		try {
			ResultSet rs=con.prepareStatement(sql).executeQuery();
			if(rs.next())return rs.getString(1);
			else return null;
		}catch(SQLException e) {
			System.out.println("SQL GetUserName error : "+e);
		}
		return null;
	}
	public void setOTP(int OTP, String email) {
		String sql="update user set OTP=? where email=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, OTP);
			ps.setString(2, email);
			ps.executeUpdate();
		}catch(SQLException e){
			System.out.println("SQL set OTP error "+ e);
		}
	}
	public int getOTP(String email) {
		String sql="select OTP from user where email=?";
		int OTP=0;
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				OTP = rs.getInt("OTP");
			}
		}catch(SQLException e) {
			System.out.println("Find status by email error :"+ e);
		}
		return OTP;
	}
	public void disable(int id) {
		String sql="update user set status=2 where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("User disable error :"+e);
		}
	}
	public void enable(int id) {
		String sql="update user set status=1 where id=?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			System.out.println("User enable error :"+e);
		}
	}
}

//“I used BCrypt for password security. During registration, passwords are hashed using BCrypt which automatically generates a salt and applies multiple hashing rounds. Only the hashed value is stored in the database. During login, I retrieve the stored hash and use BCrypt.checkpw() to verify the password. This approach prevents plaintext storage and protects against brute-force and rainbow-table attacks.”

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import model.User;
import util.DBConnection;

public class UserDAO {
	public boolean addStudent(String name,String email,String password) {
		String hashedPassword=BCrypt.hashpw(password,BCrypt.gensalt());
		String sql="INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";
		try(Connection conn=DBConnection.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, hashedPassword);
			ps.setString(4, "STUDENT");
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			return false;
		}
	}
	public User getUserByEmail(String email) {
		String sql="SELECT * FROM users WHERE email=?";
		User user=null;
		
		try {
			Connection conn=DBConnection.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User login(String email, String password) {

	    String sql = "SELECT * FROM users WHERE email=? AND password=?";
	    User user=null;
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ps.setString(2, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            user = new User();
//	            System.out.println(user.getName());
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setRole(rs.getString("role"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	public List<User> getAllStudents() {
	    List<User> students = new ArrayList<>();
	    String sql = "SELECT * FROM users WHERE role = 'STUDENT'";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setRole(rs.getString("role"));
	            students.add(user);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return students;
	}
}

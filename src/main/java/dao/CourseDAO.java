package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import util.DBConnection;

public class CourseDAO {
	public void addCourse(Course course) {
		String sql="INSERT INTO courses(title,description) VALUES(?,?)";
		try(Connection conn=DBConnection.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, course.getTitle());
			ps.setString(2, course.getDescription());
			
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<Course> getAllCourses() {
		List<Course> courses=new ArrayList<>();
		String sql = "SELECT cid, title, description FROM courses ORDER BY cid";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setcid(rs.getInt("cid"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                courses.add(course);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return courses;
	}
}

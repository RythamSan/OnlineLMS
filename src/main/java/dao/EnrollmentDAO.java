package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import util.DBConnection;

public class EnrollmentDAO {
	public boolean enrollStudent(int student_id,int course_id) {
		
		String sql="INSERT INTO enrollments(student_id,course_id) VALUES(?,?)";
		try(Connection conn=DBConnection.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setInt(1, student_id);
			ps.setInt(2, course_id);
			
			ps.executeUpdate();
			return true;
		}catch(Exception e) {
//			e.printStackTrace();
			return false;
		}
	}
//	public boolean isAlreadyEnrolled(int student_id,int course_id) {
//		String sql="SELECT 1 FROM enrollments WHERE student_id=? AND course_id=?";
//		try(Connection conn=DBConnection.getConnection();
//				PreparedStatement ps=conn.prepareStatement(sql)){
//			
//			ps.setInt(1, student_id);
//			ps.setInt(2, course_id);
//			
//			ResultSet rs=ps.executeQuery();
//			return rs.next();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	public List<Course> getEnrolledCourses(int student_id){
		
		List<Course> courses = new ArrayList<>();

        String sql = """
            SELECT c.cid, c.title,c.description
			FROM courses c
			JOIN enrollments e ON c.cid = e.course_id
			WHERE e.student_id = ?;
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setcid(rs.getInt("cid"));
                c.setTitle(rs.getString("title"));
                c.setDescription(rs.getString("description"));
                courses.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
		
		
	}
}

package controller;

import dao.CourseDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Course;
import model.User;

import java.io.IOException;

@WebServlet("/addCourse")
public class AddCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        // Safety check (filter should already block, but never trust it)
        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("unauthorized.jsp");
            return;
        }

        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
   

        CourseDAO dao = new CourseDAO();
        dao.addCourse(course);

        response.sendRedirect("admin.jsp?success=1");
    }
}

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
import java.util.List;

@WebServlet("/courses")
public class ViewCoursesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);  //Give me the existing session only if it already exists.
        //If there is no session then don't create a new session.
        User user = (User) session.getAttribute("user");

        // Safety check
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CourseDAO dao = new CourseDAO();
        List<Course> courses = dao.getAllCourses();

        request.setAttribute("courses", courses);
        try {
            request.getRequestDispatcher("courses.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
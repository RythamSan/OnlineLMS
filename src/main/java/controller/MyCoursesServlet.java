package controller;

import dao.EnrollmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Course;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/my-courses")
public class MyCoursesServlet extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null || !"STUDENT".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int studentId = user.getId();

        System.out.println(studentId);
        EnrollmentDAO dao=new EnrollmentDAO();
        List<Course> enrolledCourses =
                dao.getEnrolledCourses(studentId);


        request.setAttribute("courses", enrolledCourses);

        request.getRequestDispatcher("student_courses.jsp")
               .forward(request, response);
    }
}
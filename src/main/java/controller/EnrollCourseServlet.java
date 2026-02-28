package controller;

import dao.EnrollmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

@WebServlet("/enroll")
public class EnrollCourseServlet extends HttpServlet {

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null || !"STUDENT".equals(user.getRole())) {
            response.sendRedirect("/login.jsp");
            return;
        }
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
        String courseIdStr = request.getParameter("courseId");

        if (courseIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Course ID missing");
            return;
        }

        int courseId = Integer.parseInt(courseIdStr);
        int studentId = user.getId();

        boolean enrolled =enrollmentDAO.enrollStudent(studentId, courseId);
        if(!enrolled) {
        	session.setAttribute("msg", "Already enrolled in this course");
        }else {
        	session.setAttribute("msg", "Enrollment Successfull");
        }
//        if (!enrollmentDAO.isAlreadyEnrolled(studentId, courseId)) {
//        	session.setAttribute("msg", "Enrollment Successfull");
//            enrollmentDAO.enrollStudent(studentId, courseId);
//        }else {
//        	session.setAttribute("msg", "Already enrolled in this course");
//        }

        response.sendRedirect("my-courses");
    }
}

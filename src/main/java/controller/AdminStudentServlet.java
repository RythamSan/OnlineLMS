package controller;

import dao.CourseDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import model.Course;
import model.User;

@WebServlet("/viewStudents")
public class AdminStudentServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<User> students = userDAO.getAllStudents();

        request.setAttribute("students", students);

        try {
            request.getRequestDispatcher("/viewStudents.jsp")
                   .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
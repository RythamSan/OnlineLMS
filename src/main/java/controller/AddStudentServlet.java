package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dao.UserDAO;


@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		if(session==null || session.getAttribute("user")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserDAO dao=new UserDAO();
		boolean success=dao.addStudent(name, email, password);
		if(success) {
			request.setAttribute("message", "Student added Successfully");
		}else {
			request.setAttribute("message","Email already exists");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

}

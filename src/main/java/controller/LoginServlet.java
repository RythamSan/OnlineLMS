package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;

import dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserDAO dao=new UserDAO();
		User user=dao.login(email, password);
		System.out.println(user);
		
		if(user==null) {
			response.sendRedirect("login.jsp?error=1");
		}else {
			HttpSession session=request.getSession();
			session.setAttribute("user",user);
			if("ADMIN".equals(user.getRole())) {
				response.sendRedirect("admin.jsp");
			}else {
				response.sendRedirect("student.jsp");
			}
		}
	
	
	}
}

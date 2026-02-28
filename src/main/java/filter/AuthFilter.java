package filter;

import java.io.IOException;

import model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/student.jsp", "/admin.jsp"}) //Every HTTP request that tries to access these URLs must pass through this filter BEFORE the JSP is executed.
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        String role = user.getRole();
        String uri = req.getRequestURI();

        if (uri.endsWith("admin.jsp") && !role.equals("ADMIN")) {
            res.sendRedirect("unauthorized.jsp");
            return;
        }

      
        chain.doFilter(request, response);
    }

}

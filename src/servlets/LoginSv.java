import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;

public class LoginSv extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql;
		PrintWriter out  = response.getWriter();
		String user      = request.getParameter("user");
		String pass      = request.getParameter("password");
		HttpSession session;

		if( Socnet.authenticate(user, pass) ){
			session = request.getSession(true);
			session.setAttribute("user", user);
			session.setAttribute("admin", Socnet.isAdmin(user, pass));

			response.sendRedirect("home");			
		}
		else
			response.sendRedirect("login.jsp?msg=Wrong username or password");

	}	
}

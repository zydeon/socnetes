import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;

public class RegisterSv extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String user      = request.getParameter("user");
		String pass      = request.getParameter("password");
		
		if(Socnet.registerUser(user, pass)){
			request.getSession(true).setAttribute("user", user);
			response.sendRedirect("index.jsp");						
		}
		else{
			response.sendRedirect("register.jsp?msg=User already exists");
		}
	}
}

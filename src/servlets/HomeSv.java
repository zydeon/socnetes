import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import Socnet.*;

public class HomeSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
    response.setAttribute("chatrooms",Socnet.getChatroomNames());
	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	dispatcher.forward(request, response);
	}
}

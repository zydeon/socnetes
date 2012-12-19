import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import socnet.Socnet;

public class PostSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	request.setAttribute("chatrooms",Socnet.getChatroomPosts(request.getParameter("chatroom")));
	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	dispatcher.forward(request, response);
    }
}

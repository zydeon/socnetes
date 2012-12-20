import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class ChatSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));
	response.sendRedirect("chat.jsp");
    }
}

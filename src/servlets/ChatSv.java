import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class ChatSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	//request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));      
	String c_id=(String)request.getParameter("id");
	System.out.println(c_id);
	request.setAttribute("posts",Socnet.getChatroomPosts(c_id));      
	response.sendRedirect("chat.jsp?id="+c_id);
    }
}

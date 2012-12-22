import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class DeletePostSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	//request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));      
	String c_id=(String)request.getSession(true).getAttribute("chatroom");
	int p_id=Integer.parseInt((String)request.getParameter("p_id"));
	Socnet.deletePost(c_id,p_id);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("chat?id="+c_id);
	dispatcher.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	doGet(request,response);
    }
}

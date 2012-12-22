import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class NewPostSv extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	//request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));      
	//String cr_id=(String)request.getParameter("id");
	
	String texto=(String)request.getParameter("text");
	String path=(String)request.getParameter("pic");
	String from=(String)request.getSession(true).getAttribute("user");
	String cr_id=(String)request.getSession(true).getAttribute("chatroom");
	System.out.println("New Post Servlet: "+cr_id+" "+texto+" "+from+" "+path);
	Socnet.addPost(cr_id,texto,from,path,null);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("chat?id="+cr_id);
	dispatcher.forward(request, response);
    }
}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class EditPostSv extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	//request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));      
	//String cr_id=(String)request.getParameter("id");
	
	String text=(String)request.getParameter("text");
	int id=Integer.parseInt((String)request.getParameter("postID"));
	String path=(String)request.getParameter("pic");
	String cr_id=(String)request.getSession(true).getAttribute("chatroom");
	Socnet.editPost(cr_id,id,text,path);

	
	RequestDispatcher dispatcher = request.getRequestDispatcher("chat?id="+cr_id);
	dispatcher.forward(request, response);
    }
}

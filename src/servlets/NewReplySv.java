import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class NewReplySv extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	//request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));      
	//String cr_id=(String)request.getParameter("id");
	
	String text=(String)request.getParameter("text");
	int parent=Integer.parseInt((String)request.getParameter("parent"));
	String from=(String)request.getSession(true).getAttribute("user");
	String cr_id=(String)request.getSession(true).getAttribute("chatroom");
	Socnet.addReply(cr_id,parent,text,from);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
	dispatcher.forward(request, response);
    }
}

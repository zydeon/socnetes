import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import java.util.Date;

public class ChatSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		String c_id=(String)request.getParameter("id");
		request.getSession(true).setAttribute("chatroom", c_id);
		request.setAttribute("posts",Socnet.getChatroomPosts(c_id));
		System.out.println("Chat Servlet: "+c_id);

		RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp?id="+c_id);
		dispatcher.forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	doGet(request,response);
    }
}

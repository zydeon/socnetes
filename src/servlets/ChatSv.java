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
		request.setAttribute("posts",Socnet.getChatroomPosts(c_id));      

		RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp?id="+c_id);
		dispatcher.forward(request, response);
    }
}

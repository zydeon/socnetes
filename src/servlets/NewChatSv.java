import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;

public class NewChatSv extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	String c_name = (String)request.getParameter("chatroom_name");
	if(!c_name.equals(""))
	    Socnet.addChatroom(c_name);
	response.sendRedirect("index.jsp");
    }
}

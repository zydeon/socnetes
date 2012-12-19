import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import socnet.Socnet;
import socnet.Socnet.*;
import java.lang.String.*;

public class HomeSv extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	Socnet.addChatroom("sala_um");
	Socnet.addChatroom("sala_dois");
	request.setAttribute("chatrooms",Socnet.getChatroomThemes());
	RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	dispatcher.forward(request, response);
    }
}

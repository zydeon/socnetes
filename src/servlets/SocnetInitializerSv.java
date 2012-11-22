// import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
// import java.util.Date;
import socnet.Socnet;

public class SocnetInitializerSv extends HttpServlet {

	public void init() throws ServletException{
		Socnet.init();
	}

	public void destroy(){
		Socnet.destroy();
	}

}

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import socnet.Socnet;
import socnet.Socnet.*;
import socnet.Post;
import javax.servlet.annotation.MultipartConfig;
import java.util.Date;
import java.text.SimpleDateFormat;

@MultipartConfig()
public class NewPostSv extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	//request.setAttribute("posts",Socnet.getChatroomPosts((String)request.getParameter("id")));      
	//String chatID=(String)request.getParameter("id");

		String text = (String)request.getParameter("text");
		Part file = request.getPart("pic");
		String fileName = null;
		Date date = null;

		// System.out.println("DATE "+  );
		// System.out.println( "TIME " + request.getParameter("time") );
		// String date = 

		String from = (String)request.getSession(true).getAttribute("user");
		String chatID = (String)request.getParameter("id");

		if( file.getSize() > 0 ){
			fileName = getFilename(file);
			String currentPath = request.getSession().getServletContext().getRealPath("/");
			file.write( currentPath + "images/"+fileName );
		}
		if( request.getParameter("dateOp").equals("later") ){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String day = request.getParameter("date");
			String time = request.getParameter("time");
			try{
				date = (Date)formatter.parse(day+" "+time); 
			}
			catch(java.text.ParseException e){System.out.println(e);}			
		}

		Socnet.addPost(chatID, text, from, fileName, date);

		response.sendRedirect("chat?id="+chatID);
		// RequestDispatcher dispatcher = request.getRequestDispatcher("chat?id="+chatID);
		// dispatcher.forward(request, response);
	}

	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}	
}
<jsp:include page="auth.jsp"></jsp:include>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.Boolean"%>
<%@ page import="socnet.Post"%>
<%
   Post[] posts = (Post[])request.getAttribute("posts");
   Boolean owner = true;
%>

<html>
  <head>	
    <title></title>	
    <link rel="stylesheet" type="text/css" href="css/style.css"/>	
    <script type="text/javascript">
    function outputPost(source, text, sentDate, replyLevel, owner, ID, fileName){
	var rl = parseInt(replyLevel);
	var html =  "<br><div class='post_div' id='"+ID+"' style='position:relative;left:"+(rl*50)+"px'>";
	
	if(fileName!="null"){
	    html += "<div style='float:left;'>"+
		"<img src='images/"+fileName+"' width='100'>"+
		"</div >";
	}
	html += "<div class='pos_div'>";
	html += "FROM "+source+" <br>"+
	    "date:"+ sentDate +" "+
	    "<p>"+text+"</p>"+
	  "<button onclick=\"newReply('"+ID+"','"+(rl+1)+"')\">Reply</button>";
	if(owner=="true"){
	  html += "<button onclick=\"editPost('"+ID+"')\"> Edit </button>";
	  html +=  "<form action='deletePost' method='post'>"+
	    "<input type='submit' value='Delete'>"+
	    "<input type='hidden' name='p_id' value='"+ID+"'> "+
	    "<input type='hidden' name='id' value='"+document.getElementById("chat_name")+"'>"+
		"</form>";
	}

	html += "</div></div><br>";				

	document.write(html);
    }

function newPost(divID){
    var html = 	"<div>"+
	"<form action='newPost' method='post' enctype='multipart/form-data'>"+
	"<textarea name='text' rows='2' cols='30' placeholder='New post here'>"+
	"</textarea>"+
	"<br>"+
	"<input type='file' name='pic' accept='image/*'>"+
	"<input type='submit' value='Submit'>"+
	"</form>"+
	"</div>";

    document.write(html);
}
function newReply(parent, replyLvl){
    div = document.createElement('div');
    div.innerHTML = "<form action='newReply' method='post'>"+
	"<textarea name='text' rows='2' cols='30'>"+
	"</textarea>"+
	"<br>"+
	"<input type='hidden' name='parent' value='"+parent+"'>"+
	"<input type='hidden' name='replyLvl' value='"+replyLvl+"'>"+
	"<input type='submit' value='Submit'>"+
	"</form>";
    document.getElementById(parent).appendChild(div);
}
function editPost(divID){
    id = parseInt(divID);
    div = document.createElement('div');
    div.innerHTML = "<form action='editPost' method='post'>"+
	"<textarea name='text' rows='2' cols='30' placeholder='Edit post here'>"+
	"</textarea>"+
	"<br>"+
	"<input type='hidden' name='postID' value='"+id+"'>"+
	"<input type='submit' value='Submit'>"+
	"</form>";
    document.getElementById(divID).appendChild(div);			
}
</script>	
</head>
<body>
  <div class="sub_div">
    <%if(request.getParameter("id") != null){%>
    <h2><%=(String)request.getParameter("id")%></h2>
    <div class="input_form">
      <div>
	<form action='newPost' method='post'>
	  <textarea name='text' rows='2' cols='30' placeholder="type your text here"></textarea>
	  <br>
	  <input type='file' name='pic' accept='image/*'>
	  <input type='hidden' name='id' id='chat_name'value='<%=(String)request.getParameter("id")%>'>
	  <input type='submit' value='Submit'>
	</form>
      </div>
    </div>
    <div class="content_div">
    <%if(posts != null){%>			
    <%for(Post p : posts){   %>
    <%owner=p.getSource().equals((String)session.getAttribute("user"));%>
    <script type="text/javascript"> 
      outputPost( "<%=p.getSource()%>", "<%=p.getText()%>", "<%=p.getDate()%>", "<%=p.getReplyLevel()%>","<%=owner%>", "<%=p.getID()%>", "<%=p.getImagePath()%>" );
    </script>
    <%}%>
    <%}%>
    <%}else{%>
    <div class="input_form">
      <h3>&larr; Select Chatroom</h3>
    </div>
    <%}%>
    </div>
  </div>
  
</body>
</html>

<jsp:include page="auth.jsp"></jsp:include>

<% 
   String [] chatrooms = (String [])request.getAttribute("chatrooms");
   //if(chatrooms==null){
   //response.sendRedirect("");

   //}
   // System.out.println("->"+chatrooms);
   %>

<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <title>soc.net</title>
  </head>

  <body>
    <input type="hidden" id="username" value='<%=session.getAttribute("user")%>'>


    <div class="main_div">
      <div class="title_div">
	<h1>soc.net</h1>
	<h2>Welcome, <%=session.getAttribute("user")%> !</h2>
      </div>
      <hr>
      <div style="width:100%;">
	<div style="float:right;">
	  <form action="logout" method="get"><button>Logout</button></form>
	</div>
      </div>
      <br>
      <hr>
      <div class="list_div">
	<%if(chatrooms!=null){%>
	<select size="40" style="width:100px;">
	  <%for(String cr : chatrooms){%>
	  <option value="<%=cr%>"><%=cr%></option>
	    <%}%>
	</select>
	<%}%>
      </div>
      <div class="chatroom_div">
	<iframe class="chatroom_frame" src="chat.jsp"></iframe>
      </div>
      <br>
    </div>
  </body>
</html>

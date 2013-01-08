<jsp:include page="auth.jsp"></jsp:include>

<% 
String [] chatrooms = (String [])request.getAttribute("chatrooms");

if(chatrooms==null){
response.sendRedirect("home");
}
   // System.out.println("->"+chatrooms);

%>

<!DOCTYPE html>
<html lang="en">  
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<title>soc.net</title>
	<script type="text/javascript">
	function displayChatroom(){
		var chatroom_list = document.getElementById('chatroom_list'); 
		var cr = chatroom_list.options[chatroom_list.selectedIndex].value;
		document.getElementById('chatroom_frame').src = 'chat?id='+cr;
	}

	function newChatroom(){

	}
	</script>
</head>

<body>
	<input type="hidden" id="username" value='<%=session.getAttribute("user")%>'>
	<div class="main_div">
		<div class="title_div">
			<h1>soc.net</h1>
			<%if ( (Boolean) session.getAttribute("admin")) {%>
			<h2>Welcome, Mr. Administrator!</h2>
			<%}else{%>
			<h2>Welcome, <%=session.getAttribute("user")%> !</h2>
			<%}%>
		</div>
		<hr>
		<div class="content_div">
			<%if ( (Boolean) session.getAttribute("admin")) {%>
			<div style="float:left;">
				<form method="post" action="newChat">
					<input type="submit" value="Create Chatroom">
					<input type="text" name="chatroom_name" id="chatroom_name" placeholder="Chatroom Name">
				</form>
			</div>
			<%}%>
			<div style="float:right;">
				<form action="logout" method="get"><button>Logout</button></form>
			</div>
		</div>
		<br>
		<hr>
		<div class="list_div" float="left">
			<%if(chatrooms!=null){%>
			<select id="chatroom_list" size="32" style="width:100px;" onchange="displayChatroom()">
				<%for(String cr : chatrooms){%>
					<option value="<%=cr%>"><%=cr%></option>
				<%}%>
			</select>
			<%}%>			
		</div>
		<div class="chatroom_div" float="left">
			<iframe class="chatroom_frame" id="chatroom_frame" src="chat.jsp"></iframe>
		</div>
		<br>
	</div>
</body>
</html>

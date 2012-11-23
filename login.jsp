<%
	String msg = request.getParameter("msg");
	if(msg!=null)
		out.println( "<span style='color:red'>*"+msg+"</span>" );
%>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <title>soc.net</title>
  </head>
  
  <body>
    <br>
    <div class="main_div">
      <div>
	<h1>soc.net</h1>
      </div>
      <div class="desc_div">
	<h>soc.net - ES</h>
	<p>Trabalho realizado por:</p>
	<p>
	  Carlos Ferreira<br>
	  Jo&atilde;o Valen&ccedil;a<br>
	  Pedro Matias
	</p>
      </div>
      
      <div class="login_div">
	<h>Login</h>
	<form action="login" method="post">
	  <input type="text" name="user" placeholder="Username"> <br>
	  <input type="password" name="password" placeholder="Password"> <br>
	  <br>
	  <input type="submit" name="enter" id="enter" value="Login">
	</form>
	<br>
	<form method="link" action="register.jsp">
	  <input type="submit" value="Register">
	</form>
	
      </div>
    </div>
  </body>
</html>

<%
	String msg = request.getParameter("msg");
	if(msg!=null)
		out.println( "<span style='color:red'>*"+msg+"</span>" );
%>
<span></span>
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
      <div class="description_div">
	<h4>Soc.net</h4>
	<p>Trabalho realizado por:</p>
	Carlos Ferreira<br>
	Jo&atilde;o Valen&ccedila<br>
	Pedro Matias	
      </div>
      
      <div class="login_div">
	<form action="login" method="post" >
	  <input type="text" name="user" placeholder="Username"> <br>
	  <input type="password" name="password" placeholder="Password"> <br>
	  <input type="submit" name="enter" id="enter"value="Login">
	</form>
	
	<form method="link" action="register.jsp">
	  <input type="submit" value="Register">
	</form>
	
      </div>
    </div>
  </body>
</html>

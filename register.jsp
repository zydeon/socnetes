<h1>soc.net</h1>
<h2>Registration</h2>

<%
	String msg = request.getParameter("msg");
	if(msg!=null)
		out.println( "<span style='color:red'>*"+msg+"</span>" );
%>

<form action="register" method="post"  onsubmit="return checkfields();">
	User <input type="text" name="user" id="user" placeholder="Username"> <br>
	Password<input type="password" name="password" id="password" placeholder="Password"> <br>
	Confirm password<input type="password" name="cpassword" id="cpassword" placeholder="Confirm Password"> <br>

	<input type="submit" name="register" value="Register">
</form>

<script type="text/javascript">
	
	function checkfields(){ 
		if(document.getElementById('user').value=='' || document.getElementById('user').value==null){
			alert("Invalid Username");
			document.getElementById('user').focus();
			return false; 
		}
		
		if(document.getElementById('password').value=='' || document.getElementById('password').value==null){
			alert("Invalid Password");
			document.getElementById('password').focus();
			return false; 
		}
		
		if(document.getElementById('password').value != document.getElementById('cpassword').value) { 
			alert("Your Passwords do not match.");
			document.getElementById('password').focus();
			return false; 
		}
		//obj.option[obj.selectedIndex]	
	}

</script>






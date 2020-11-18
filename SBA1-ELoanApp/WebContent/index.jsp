<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan system</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
	<hr>
<div align=center>
	<h2>eLoan Login</h2>
	<form action="login" method="post">
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid" required> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="text" id="password" name="password" required> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
		<a href = register.jsp>New User? Register here</a>
	</form>
	</div>
	</div>
<hr/>

</body>
</html>
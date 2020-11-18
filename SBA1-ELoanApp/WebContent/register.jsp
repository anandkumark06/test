<html>
<head>
<title>New User - Registration Form</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<form action="registernewuser" method="POST"  >
	<table border="0" cellspacing="0" cellpadding="0" width="40%" >
			<tr><td><strong>User Name</strong></td> 
			<td><em><input type="text" name="userName" required /></em></td>
			</tr>
			<tr><td><strong>Date Of Birth</strong></td> 
			<td><em><input type="date" name="dateOfBirth" required /></em></td>
			</tr>
			<tr><td><strong>Gender</strong></td> 
				<td> <div class="Gender">
        			<input type="radio" name="gender" value="Male" CHECKED/><label for="male">Male</label>
        			<input type="radio" name="gender" value="Female" /><label for="female">Female</label>
      				</div>
      			</td>
			</tr>
			<tr><td><strong>Email Id</strong></td> 
			<td><em><input type="text" name="emailId" required /></em></td>
			</tr>
			<tr><td><strong>Password</strong></td> 
			<td><em><input type="password" name="pwd" required /></em></td>
			</tr>
			<tr><td><button>Submit</button></td> 
			<td><em></em></td>
			</tr>
	</table>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
<html>
<head>
<title>Loan Application Form</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h4>User - Loan Application Form</h4>
<h4>Welcome ${user.username} !! </h4>
<div align="right"><a href="index.jsp">Logout</a></div>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body onload="myFunction()">

<!--write the html code to accept laon info from user and send to placeloan servlet -->
<form action="placeloan" method="POST"  >
	<table border="0" cellspacing="0" cellpadding="0" width="40%" >
			<tr><td><strong>User Name</strong></td> 
			<td><em>${user.username}</em></td>
			</tr>
			<tr><td><strong>Loan Purpose</strong></td> 
			<td><em><input type="text" name="purpose" required /></em></td>
			</tr>
			<tr><td><strong>Loan Name</strong></td> 
			<td><em><input type="text" name="loanName" required /></em></td>
			</tr>
			<tr><td><strong>Loan Amount</strong></td> 
			<td><em><input type="number" name="amtRequested" required /></em></td>
			</tr>
			<tr><td><strong>Business Structure</strong></td> 
			<td><select name="bstructure">
					<option></option>
					<option>Individual</option>
					<option>Organization</option>
					</select></td>
			</tr>
			<tr><td><strong>Billing Indicator</strong></td> 
			<td><em><input type="text" name="bindicator" required /></em></td>
			</tr>
			<tr><td><strong>Mobile</strong></td> 
			<td><em><input type="number" name="mobile" required /></em></td>
			</tr>
			<tr><td><strong>Address</strong></td> 
			<td><em><input type="text" name="address" required /></em></td>
			</tr>
			<tr><td><strong>Loan Term (Months)</strong></td> 
			<td><em><input type="number" name="term"  min=12 max = 120 step =1 required /></em></td>
			</tr>
			<tr><td>
				<button>Submit</button>
			<td><em></em></td>
			</tr>
	</table>
</form>
<a href="userhome.jsp">Go Back</a><br>

<jsp:include page="footer.jsp"/>
</body>
</html>
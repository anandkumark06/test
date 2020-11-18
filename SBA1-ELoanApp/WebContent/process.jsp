<html>
<head>
<title>Admin - Process Loan</title>
</head>
<body>
<jsp:include page="header.jsp"/>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page -->
<form action="processloan" method="post">

		<table border="0" cellspacing="0" cellpadding="0" width="40%" >
			<tr><td><strong>Enter application id to process</strong></td> 
			<td><em><input type="text" name="applId" required /></em></td>
			</tr>
			<tr><td><button>Approve</button></td> 
			<td><a href= "loanDetails.jsp">Reject</a></td>
			</tr>
			</table>
	</form>
	<a href="adminhome1.jsp">Go Back</a><br>
	
<jsp:include page="footer.jsp"/>

</body>
</html>
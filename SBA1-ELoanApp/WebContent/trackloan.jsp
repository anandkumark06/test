<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan - Track Loan</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h4>Loan - Track Loan</h4>
<h4>Welcome ${user.username} !! </h4>
<div align="right"><a href="index.jsp">Logout</a></div>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information-->
		<table>
			<tr> <td><strong>User Name</strong></td>
				<td><em>${user.username}</em></td></tr>
			<tr><td><strong>Loan Status</strong></td>
				<td><em>${loanDetails.status}</em></td></tr>
			<tr><td><strong>Application ID</strong></td>
				<td><em>${loanDetails.applno}</em></td></tr>
			<tr><td><strong>Amount Requested</strong></td>
				<td><em>${loanDetails.amtrequest}</em></td></tr>
			<tr><td><strong>Loan Purpose</strong></td>
				<td><em>${loanDetails.purpose}</em></td></tr>
			<tr><td><strong>Loan Application Date</strong></td>
				<td><em>${loanDetails.doa}</em></td></tr>
			<tr><td><strong>Business Structure</strong></td>
				<td><em>${loanDetails.bstructure}</em></td></tr>
			<tr><td><strong>Billing Indicator</strong></td>
				<td><em>${loanDetails.bindicator}</em></td></tr>
			<tr><td><strong>Address</strong></td>
				<td><em>${loanDetails.address}</em></td></tr>
			<tr><td><strong>Loan Term</strong></td>
				<td><em>${loanDetails.term} months</em></td></tr>
		</table>
<a href="userhome.jsp">Go Back</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan - Edit Loan</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h4>Loan - Edit Loan Application</h4>
<h4>Welcome ${user.username} !! </h4>
<div align="right"><a href="index.jsp">Logout</a></div>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information-->
<form action = "editLoanProcess" method="POST">
		<table>
			<tr> <td><strong>User Name</strong></td>
				<td><em>${user.username}</em></td></tr>
			<tr><td><strong>Loan Status</strong></td>
				<td><em>${loanDetails.status}</em></td></tr>
			<tr><td><strong>Application ID</strong></td>
				<td><em>${loanDetails.applno}</em></td></tr>
			<tr><td><strong>Amount Requested</strong></td>
				<td><input type="number" name="amtrequest" value="${loanDetails.amtrequest}" /></td></tr>
			<tr><td><strong>Loan Purpose</strong></td>
				<td><input type="text" name="purpose" value="${loanDetails.purpose}" /></td></tr>
			<tr><td><strong>Loan Application Date</strong></td>
				<td><em>${loanDetails.doa}</em></td></tr>
			<tr><td><strong>Business Structure</strong></td>
				<td><input type="text" name="bstructure" value="${loanDetails.bstructure}" /></td></tr>
			<tr><td><strong>Billing Indicator</strong></td>
				<td><input type="text" name="bindicator" value="${loanDetails.bindicator}" /></td></tr>
			<tr><td><strong>Address</strong></td>
				<td><input type="text" name="address" value="${loanDetails.address}" /></td></tr>
			<tr><td><strong>Loan Term</strong></td>
				<td><em>${loanDetails.term} months</em></td></tr>
			<tr><td><button>Save</button></td><td><em></em></td></tr>
		</table> </form>
<a href="userhome.jsp">Go Back</a><br>

<jsp:include page="footer.jsp"/>
</body>
</html>
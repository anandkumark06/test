<%@page import="com.iiht.evaluation.eloan.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h4>User Dash Board</h4>
<h4>Welcome ${user.username} !! </h4>
<div align="right"><a href="index.jsp">Logout</a></div>
<a href="application.jsp">Apply for Loan</a><br>
<a href="trackloan">Track Loan Application</a><br>
<a href="editloan">Edit Loan Application</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>
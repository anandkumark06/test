<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.iiht.evaluation.eloan.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration success</title>
</head>
<body>
	<!-- read user name and password from user to create account and send to usercontrollers registeruser method -->
	<% User newUser = (User)request.getAttribute("newUser");%>
	User <% newUser.getUsername(); %>registered successfully. <a href="index.jsp">Login here</a>
	
</body>
</html>
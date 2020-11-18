<%@page import="com.iiht.evaluation.eloan.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan - Success</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h4>Edit Loan - Success</h4>
<h4>Welcome ${user.username} !! </h4>
<div align="right"><a href="index.jsp">Logout</a></div>

Your loan request is updated successfully. You can track/edit your loan as below:

<a href="userhome.jsp">Go Back</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>
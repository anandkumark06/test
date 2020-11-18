<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
<jsp:include page="header.jsp"/>

	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method -->
 <c:choose>
			<c:when test="${dispLoansInfo==null }">
				<p>No Open Loans to display</p>
			</c:when>
			<c:otherwise>
				<table>
					<thead>
						<tr bgcolor="#FFF0FF" height="30" width="100%" valign="top" align="center">
							<th>Application ID</th>
							<th>UserName</th>
							<th>Amount Requested</th>
							<th>Application Date</th>
							<th>Business Structure</th>
							<th>Billing Indicator</th>
							<th>Purpose</th>
							<th>Mobile</th>
							<th>Address</th>
							<th>Loan Term</th>
							<th>Status</th>
					</thead>
					<tbody>
						<c:forEach var="c" items="${dispLoansInfo }">
							<tr bgcolor="#E3E4FA" height="25" width="100%" valign="top">
								<td>${c.applno }</td>
								<td>${c.username }</td>
								<td>${c.amtrequest }</td>
								<td>${c.doa }</td>
								<td>${c.bstructure }</td>
								<td>${c.bindicator }</td>
								<td>${c.purpose }</td>
								<td>${c.mobile }</td>
								<td>${c.address }</td>
								<td>${c.term }</td>
								<td>${c.status }</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
			
		</c:choose>
<a href="adminhome1.jsp">Go Back</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>
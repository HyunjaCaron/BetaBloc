<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders List</title>
<%@include file ="header.jsp" %>
</head>
<body>
<h1>Orders</h1>
<TABLE>
<tr> 
<td><h3>Order Number</h3></td> 
<td><h3>User</h3></td>
<td><h3>Date</h3></td>
</tr>
<c:forEach var="eachOrder" items="${listOfOrders}">

<TR><TD> <c:out value="${eachOrder.orderId}" /> </TD>

<TD> <c:out value="${eachOrder.user}" /> </TD>

<TD> <c:out value="${eachOrder.date}" /> </TD>


</c:forEach>

</TABLE>
</body>
</html>
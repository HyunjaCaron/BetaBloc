<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
<%@include file ="header.jsp" %>
</head>
<body>
<h1>User Information</h1>
<TABLE>
<tr> 
<td><h3>UserName</h3></td> 
<td><h3>Name</h3></td>
<td><h3>Password</h3></td>
</tr>
<c:forEach var="eachUser" items="${listOfUsers}">

<TR><TD> <c:out value="${eachUser.userId}" /> </TD>

<TD> <c:out value="${eachUser.name}" /> </TD>

<TD> <c:out value="${eachUser.password}" /> </TD>

</c:forEach>

</TABLE>

</body>
</html>
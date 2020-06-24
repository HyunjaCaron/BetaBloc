<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose A Card</title>
<%@include file ="header.jsp" %>
</head>
<body>
Please choose the Card you wish to use for this order: <br> 

<TABLE style="border:none">
<c:forEach var="eachCard" items="${cards}">

<TR><TD> <c:out value="${eachCard.cardId}" /> </TD>
<TD><sf:form action="${pageContext.request.contextPath}/placeOrder/${eachCard.cardId}">
	<input type="submit" name="commit" value="choose" />

		</sf:form>
		</TD>
</c:forEach>
</TABLE>
<a href="${pageContext.request.contextPath}/inputCardInfo">ADD A CARD</a>

</body>
</html>
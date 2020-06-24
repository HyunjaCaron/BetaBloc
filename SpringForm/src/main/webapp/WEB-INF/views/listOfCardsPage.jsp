<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Card List</title>
<%@include file ="header.jsp" %>
</head>
<body>
<h1>Cards</h1>

<TABLE>
<TR> 
<td><h3>Card Number</h3></td>
<td><h3>Balance</h3></td>
</TR>
<c:forEach var="eachCard" items="${listOfCards}">

<TR><TD> <c:out value="${eachCard.cardId}" /> </TD>

<TD> <c:out value="${eachCard.balance}" /> </TD>

<TD><sf:form action="${pageContext.request.contextPath}/addMoneyToCard/${eachCard.cardId}">
	<input type="text"  name="money" value="${param.money}">
	<input type="submit" name="commit" value="add Money" />

		</sf:form>
		</TD>
</c:forEach>
</TABLE>
<br>
<a class="checkoutbuttons" href="${pageContext.request.contextPath}/inputCardInfo">ADD A CARD</a>    
<a class="checkoutbuttons" href="${pageContext.request.contextPath}/chooseCardToRemove">REMOVE A CARD</a>
</body>
</html>
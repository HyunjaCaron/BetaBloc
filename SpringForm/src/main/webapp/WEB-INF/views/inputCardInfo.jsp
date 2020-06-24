<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Input Card Details</title>
<%@include file ="header.jsp" %>
</head>
<body>
<h1>Card Details</h1>

	<sf:form action="addACard" method="POST" modelAttribute="card">
		<sf:label path="cardId">Card Number</sf:label>
		<sf:input path="cardId" size="30" placeholder= "card number" required="true" />
		<br />
		<sf:label path="balance">Card Balance</sf:label>
		<sf:input path="balance" size="30" placeholder= "card balance" required="true"/>
		<input type="submit" name="commit" value="submit" />
	</sf:form>
</body>
</html>
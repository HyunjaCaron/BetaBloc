<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose A Card</title>
<%@include file ="header.jsp" %>
</head>
<body>
Please choose the Card you wish to remove: <br>

<TABLE style="border:none">
<c:forEach var="eachCard" items="${cards}">

<TR><TD> <c:out value="${eachCard.cardId}" /> </TD>
<TD><sf:form action="${pageContext.request.contextPath}/removeCard/${eachCard.cardId}">
	<input type="submit" name="commit" value="choose" />

		</sf:form>
		</TD>
</c:forEach>
</TABLE>
</body>
</html>
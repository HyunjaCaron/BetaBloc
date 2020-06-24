<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Climbing Gear</title>
<%@include file ="header.jsp" %>
</head>
<body>
<h1>Climbing Gear</h1>

    	<a href="${pageContext.request.contextPath}/listTheProducts?filter=price%20desc">List By Price: High To Low</a> <br>
		<a href="${pageContext.request.contextPath}/listTheProducts?filter=price%20asc">List By Price: Low To High</a>

<TABLE>
<tr> 
<td><h3>Product</h3></td> 
<td><h3>Price</h3></td>
</tr>
<c:forEach var="eachProduct" items="${listOfProducts}">

<TR>
	<td> <img src="${pageContext.request.contextPath}/resources/${eachProduct.img}" style="width:100px;"/></td> 
	<TD> <c:out value="${eachProduct.name}" /> </TD>
	
	<TD> <c:out value="${eachProduct.price}" /> </TD>


	<td>

		<sf:form action="${pageContext.request.contextPath}/addItemToBasket/${eachProduct.productId}">

	<input type="submit" name="commit" value="add" />

		</sf:form>

	</td>
	
</TR>

</c:forEach>

</TABLE>
</body>
</html>
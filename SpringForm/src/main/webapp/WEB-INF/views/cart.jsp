<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/projectcss.css" />
<title>My Cart</title>
<%@include file ="header.jsp" %>
</head>
<body>

<h1>My Cart</h1>
<Table>

<tr> 
<td></td> 
<td>Product</td> 
<td>Quantity</td>
<td>Price</td>
</tr>
<c:forEach var="eachEntry" items="${cartOfProducts}">

<tr>
<td> <img src="${pageContext.request.contextPath}/resources/${eachEntry.key.img}" style="width:100px;"/></td> 
<td><c:out value="${eachEntry.key.name}" /></td> 
<td><c:out value="${eachEntry.value}" /></td>
<td><c:out value="${eachEntry.key.price}" /></td>
	<td>
	<sf:form action="${pageContext.request.contextPath}/removeProductFromCart/${eachEntry.key.productId}">
		<input type="submit" name="commit" value="remove" />
	</sf:form>
	</td>
</tr>
</c:forEach>
<tr><td><c:out value="Total:  ${total}" /></td></tr>
</Table>
<br>
<br>

<a class="checkoutbuttons" href="${pageContext.request.contextPath}/clearCart">CLEAR</a>
<a class="checkoutbuttons" href="${pageContext.request.contextPath}/OrderConfirmationPage">PLACE ORDER</a>
<a class="checkoutbuttons" href="${pageContext.request.contextPath}/listTheProducts">CONTINUE SHOPPING</a>

</body>
</html>
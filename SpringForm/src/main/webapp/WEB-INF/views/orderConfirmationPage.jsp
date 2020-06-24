<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/projectcss.css" />
<title>Order Confirmation</title>
<%@include file ="header.jsp" %>
</head>
<body>

Are you sure that you want to place your order? <br>
<a class="checkoutbuttons" href="${pageContext.request.contextPath}/cardChoice">PLACE ORDER</a>
<a class="checkoutbuttons" href="${pageContext.request.contextPath}/cart">RETURN TO CART</a>

</body>
</html>
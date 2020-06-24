<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/projectcss.css" />
<title>Beta Bloc</title>
<%@include file ="header.jsp" %>
</head>
<body> 

Hi ${userId}, Welcome to BetaBloc! We have all your climbing needs.
<br>
<Table style="border:none ">

	<tr>
	<td>
    <a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=hangboard">
	<img src="${pageContext.request.contextPath}/resources/Hangboard_Icon.jpg" alt="HangBoards" style="width:200px" class="link">
	</a>
	</td>
	<td>
	<a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=shoes">
	<img src="${pageContext.request.contextPath}/resources/shoes.png" alt="Shoes" style="width:200px" class="link">
	</a>
	</td>
	<td>
	<a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=sportclimbing">
	<img src="${pageContext.request.contextPath}/resources/rope2.png" alt="Sport Climbing" style="width:200px" class="link">
	</a>
	</td>
	<td>
	<a  href="${pageContext.request.contextPath}/listTheProductsByCategory?category=tradclimbing">
	<img src="${pageContext.request.contextPath}/resources/carabiner.png" alt="Trad Climbing" style="width:200px" class="link">
	</a>
	</td>
	</tr>
		<tr>
	<td style="text-align:center">HangBoards</td>
	<td style="text-align:center">Shoes</td>
	<td style="text-align:center">Sport Climbing</td>
	<td style="text-align:center">Trad Climbing</td>
	</tr>
 </Table>



</body>
</html>
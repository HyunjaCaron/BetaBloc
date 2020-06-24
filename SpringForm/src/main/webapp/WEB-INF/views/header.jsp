<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/template.css" />
<link rel = "stylesheet" href="${pageContext.request.contextPath}/resources/projectcss.css" />
<title>Insert title here</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/welcome">
  			<img id="betablocklogo" src="${pageContext.request.contextPath}/resources/betabloclogo.png" alt="logo" class="link">
	</a>
	<div class="topnavbar">
	
	<Table style="border:none">
		<tr><td style="width: 40%">
			<sf:form action="submitSearch" method="POST">
			<input id = "topnavsearch" name="search" type="text" placeholder="Search..." >
			<input type="submit" name="commit" value="search" />
			</sf:form>
		</td> 
			 
			<td>
			<c:choose>
            <c:when test="${empty sessionScope.userId}">
                <a class="topnavlinks" href="${pageContext.request.contextPath}/login">LOGIN</a>
                <a class="topnavlinks" href="${pageContext.request.contextPath}/register">REGISTER</a>
            </c:when>
            <c:otherwise>

            	<a class="topnavlinks" href="${pageContext.request.contextPath}/welcome">HOME</a>
                <a class="topnavlinks" href="${pageContext.request.contextPath}/logout">LOGOUT</a>
                <a class="topnavlinks" href="${pageContext.request.contextPath}/listTheOrdersForAUser"> MY ORDERS </a>
				<a class="topnavlinks" href="${pageContext.request.contextPath}/listTheUserInfoForAUser"> MY INFO </a>
				<a class="topnavlinks" href="${pageContext.request.contextPath}/listCardsForAUser"> MY CARDS </a>

            </c:otherwise>
        	</c:choose>		
                <div class="dropdown">
  				<a class="topnavlinks">CLIMBING GEAR</a>
 					<div class="dropdown-content">
    					<a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=hangboard">HANGBOARDS</a>
    					<a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=shoes">CLIMBING SHOES</a>
    					<a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=sportclimbing">SPORT CLIMBING</a>
    					<a href="${pageContext.request.contextPath}/listTheProductsByCategory?category=tradclimbing">TRAD CLIMBING</a>
  					</div>
  				</div>
  					
  				

<!--				<a class="topnavlinks" href="${pageContext.request.contextPath}/listTheUsers"> USER LIST </a> 
				<a class="topnavlinks" href="${pageContext.request.contextPath}/listTheCards"> CARD LIST </a> 
				<a class="topnavlinks" href="${pageContext.request.contextPath}/listTheOrders"> ORDER LIST </a>  -->
				<a class="topnavlinks" href="${pageContext.request.contextPath}/cart"> MY CART </a>
				
				</td></tr>

            </Table>
               
        </div>

</body>
</html>
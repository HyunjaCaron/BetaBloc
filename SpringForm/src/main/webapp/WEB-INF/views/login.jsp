<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login Page</title>
<%@include file ="header.jsp" %>
</head>
<body>

	<h1>Login</h1>

	<sf:form action="submitLogin" method="POST" modelAttribute="user">
		<sf:label path="userId">Username</sf:label>
		<sf:input path="userId" size="30" placeholder= "username" required="true" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" placeholder= "password" required="true"/>
		<input type="submit" name="commit" value="submit" />
	</sf:form>

	<B>${requestScope.message}</B><br/><br/><br/>


	<!-- img src="<c:url value="/resources/image.jpg" />"> -->

</body>
</html>
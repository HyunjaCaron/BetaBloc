<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register Page</title>
<%@include file ="header.jsp" %>
</head>
<body>

<h1>Register</h1>

	
	<sf:form action="submitRegister" method="POST" modelAttribute="user" >
		<sf:label path="userId">Username</sf:label>
		<sf:input path="userId"  size="30" required="true"/>
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" required="true"/>
		<br />
		<sf:label path="email">Email</sf:label>
		<sf:input path="email" size="30" />
		<br />
		<sf:label path="name">Name</sf:label>
		<sf:input path="name" size="30" />
		<br />
		<input type="submit" name="commit" value="submit" />	
	</sf:form>

	<B>${requestScope.message}</B><br/><br/><br/>



</body>
</html>
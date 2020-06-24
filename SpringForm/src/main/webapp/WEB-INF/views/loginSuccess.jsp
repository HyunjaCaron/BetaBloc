<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Login Success page</title>
<%@include file ="header.jsp" %>
</head>
<body>
	<h1>You have Logged In!</h1>

	Here are the details you entered:	<BR>
	Username: ${user.userId}   <BR>
	Password: ${user.password}   <BR>

</body>
</html>
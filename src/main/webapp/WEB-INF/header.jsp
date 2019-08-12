<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
 <c:url value="/resources/styles/header.css" var="headerStyle" />
<link type="text/css" rel="stylesheet" href="${headerStyle}"/>

</head>

<style type = "text/css">


</style>

<body>

<ul id="header" class = "navigation">
    <li><a href="${pageContext.request.contextPath}/tests">Tests</a></li>
    <li><a id="usr" href="${pageContext.request.contextPath}/user_info">Profile</a></li>
    <li><a id="logout" href="app/?command=Logout" class = "button">Logout</a></li>
</ul>

</body>

</html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

<head>

</head>

<style>
    #header {
        position: absolute;
        width: 100%;
        height: 67px;
        left: 0px;
        top: 0px;
        background: #000000;
    }

    #logout {
        position: absolute;
        width: 52px;
        height: 21px;
        left: 1650px;
        top: 10px;
        font-family: Roboto;
        font-style: normal;
        font-weight: bold;
        font-size: 18px;
        line-height: 21px;
        color: #2CB3FE;
        text-decoration: none;
    }

    #tests {
        position: absolute;
        width: 52px;
        height: 21px;
        left: 1450px;
        top: 10px;
        font-family: Roboto;
        font-style: normal;
        font-weight: bold;
        font-size: 18px;
        line-height: 21px;
        color: #ffffff;
        text-decoration: none;

    }

    #usr {
        position: absolute;
        width: 52px;
        height: 21px;
        left: 1250px;
        top: 10px;
        font-family: Roboto;
        font-style: normal;
        font-weight: bold;
        font-size: 18px;
        line-height: 21px;
        color: #ffffff;
        text-decoration: none;
    }
</style>


<c:set var="logOutReq" value="${pageContext.request}" />
<h3 value="${logOutReq}"></h3>

<header id="header">
    <a id="logout" href="/?command=Logout" class="button">Logout</a>
    <a href="/tests">Tests</a>
    <a id="usr" href="/user_info">Profile</a>
</header>


</html>
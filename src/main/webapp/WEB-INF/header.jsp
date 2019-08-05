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
</style>

<body>
    <c:set var="logOutReq" value="${pageContext.request}" />
    <h3 value="${logOutReq}"></h3>
    <header id="header">
        <a id="logout" href="/?command=Logout" class="button">Logout</a>
    </header>

</body>

</html>
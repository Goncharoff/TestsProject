<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale = 1, shtink-to-fir = no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <c:url value="/resources/styles/register_style.css" var="registerStyle" />
    <link type="text/css" rel="stylesheet" href="${registerStyle}" />
    <%-- <link type="text/css" rel="stylesheet" href="/resources/styles/register_style.css" />  --%>
</head>

<body>

    <div id="container" class = "container">
        <form method="POST" action="app/?command=Login" id="login-form" class = "container form-item" enctype='application/json'>
            <h3 id="login-text" class="text-center text-white pt-5" color: #000000>Login</h3>
            <input id="email-text" type="email" placeholder="E-mail" name="userEmail" class = "container form-item" required>
            <input id="password-text" type="password" placeholder="password" name="userPassword" class = "container form-item"
                required>
            <button id="submit" class = "form-item">Login</button>
        </form>
        <a href='<c:url value = "/registration"/>'>Don't have account? Register!</a>
    </div>

</body>

<script type="text/javascript">

    $("#login-form").submit(function (e) {
        event.preventDefault(event);
        var post_url = $(this).attr("action");
        var request_method = $(this).attr("method");
        var userInfo = {
            "userEmail": $("#email-text").val(),
            "userPassword": $("#password-text").val()
        }

        $.ajax({
            url: post_url,
            type: "POST",
            data: JSON.stringify(userInfo),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                window.location.replace(data.redirect);
            }
        })
    });


</script>

</html>
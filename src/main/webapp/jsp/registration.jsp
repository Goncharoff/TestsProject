<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <c:url value="/resources/styles/register_style.css" var="registerStyle" />
    <link type="text/css" rel="stylesheet" href="${registerStyle}" />
</head>

<body>
   

    <div id="container-main" class="container">
        <form method="POST" action="app/?command=Register" id="registration-form" enctype='application/json'
            class="container form">
            <h2 id="login-text" class="container form-item" color: #000000>Register</h3>
                <input id="user-name" class="container form-item" type="text" placeholder="Name" name="userName"
                    required>
                <input id="user-surname" class="container form-item" type="text" placeholder="Surname"
                    name="userSurname" required>
                <input id="email-text" class="container form-item" type="email" placeholder="E-mail" name="userEmail"
                    class="input-data" required>
                <input id="password-text" class="container form-item" type="password" placeholder="password"
                    name="userPassword" class="input-data" required>
                <input id="repeated-password" class="container form-item" type="password" placeholder="repeat password"
                    name="repeatedPassword" class="container input-data" required>

                <button id="submit" class="form-item">Register</button>
        </form>
            <a href='<c:url value = "/login"/>'>Already have account?</a>
    </div>

</body>
  
    <script type="text/javascript">

    $("#registration-form").submit(function (e) {
        event.preventDefault(event);
        var post_url = $(this).attr("action");
        var request_method = $(this).attr("method");
        var userInfo = {
            "userEmail": $("#email-text").val(),
            "userPassword": $("#password-text").val(),
            "userName": $("#user-name").val(),
            "userSurname": $("#user-surname").val()
        }

        $.ajax({
            url: post_url,
            type: "POST",
            data: JSON.stringify(userInfo),
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
                window.location.replace(data.redirect);
            } ,
            error: function() {
                alert("Wrong input paramters repeat please");
            }
        })
    });
</script>

</html>
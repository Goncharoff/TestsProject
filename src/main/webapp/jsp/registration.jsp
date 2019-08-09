<html>

<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<style>
    body {
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        font-size: 18px;
        line-height: 21px;
        color: #CBCBCB;
    }

    #login-form {
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    .form-item {
        margin-bottom: 32px;
        padding: 12px 24px;
        width: 300px;
        border-radius: 5px;

    }


    .form-item:last-child {
        margin-bottom: 0;
        padding: 12px 24px;
        width: 100px;
        border-radius: 5px;
    }
</style>

<body>
    <div id="container">
        <form method="POST" action="/?command=Register" id="registration-form" enctype='application/json'>
            <h3 id="login-text" class="form-item" color: #000000>Register</h3>
            <input id="user-name" class="form-item" type="text" placeholder="Name" name="userName" required>
            <input id="user-surname" class="form-item" type="text" placeholder="Surname" name="userSurname" required>
            <input id="email-text" class="form-item" type="email" placeholder="E-mail" name="userEmail"
                class="input-data" required>
            <input id="password-text" class="form-item" type="password" placeholder="password" name="userPassword"
                class="input-data" required>
            <input id="repeated-password" class="form-item" type="password" placeholder="password"
                name="repeatedPassword" class="input-data" required>

            <button id="submit" class="form-item">Register</button>
        </form>
    </div>

</body>     

<script type="text/javascript">

    $("#registration-form").submit(function (e) {
        event.preventDefault(event);
        var post_url = $(this).attr("action");
        var request_method = $(this).attr("method");

        var userInfo = {
            "userName": $("#user-name").val(),
            "userSurname": $("#user-surname").val(),
            "userEmail": $("#email-text").val(),
            "userPassword": $("#password-text").val()
        }   

        console.debug(userInfo);

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
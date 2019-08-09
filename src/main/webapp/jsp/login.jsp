<html>

<head>
    <meta charset="utf-8">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>

<style>
    #login-text {
        position: absolute;
        width: 74px;
        height: 52px;
        left: calc(50% - 74px/2);
        top: calc(45% - 52px/2 - 117px);
        font-family: Roboto;
        font-style: normal;
        font-weight: bold;
        font-size: 36px;
        line-height: 42px;
    }

    #email-text {
        position: absolute;
        width: 340px;
        height: 42px;
        left: calc(50% - 340px/2);
        top: calc(50% - 42px/2 - 44px);
        border: 1px solid #C4C4C4;
        box-sizing: border-box;
        border-radius: 10px;
    }

    #password-text {
        position: absolute;
        width: 340px;
        height: 43px;
        left: calc(50% - 340px/2);
        top: calc(50% - 43px/2 + 21.5px);
        border: 1px solid #C4C4C4;
        box-sizing: border-box;
        border-radius: 10px;
    }

    #submit {
        position: absolute;
        width: 176px;
        height: 43px;
        left: calc(50% - 176px/2);
        top: calc(50% - 43px/2 + 109.5px);
        background: #000000;
        border-radius: 10px;
        color: #FFFFFF;
        font-family: Roboto;
        font-style: normal;
        font-weight: bold;
        font-size: 18px;
        line-height: 21px;
    }

    .input-data {
        position: absolute;
        width: 94px;
        height: 19px;
        left: calc(50% - 94px/2 - 109px);
        top: calc(50% - 19px/2 - 45.5px);
        font-family: Roboto;
        font-style: normal;
        font-weight: normal;
        font-size: 18px;
        line-height: 21px;
        color: #CBCBCB;
        padding: 0 2.5em 0 0.5em;
    }
</style>

<body>
    <div id="container">
        <form method="POST" action="/?command=Login" id="login-form" enctype='application/json'>
            <h3 id="login-text" class="text-center text-white pt-5" color: #000000>Login</h3>
            <input id="email-text" type="email" placeholder="E-mail" name="userEmail" class="input-data" required>
            <input id="password-text" type="password" placeholder="password" name="userPassword" class="input-data"
                required>
            <button id="submit">Login</button>
        </form>
        <a href="/registration">Registration</a>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
  <link rel="stylesheet" type="text/css" href="/styles/login_style.css"/>
   <script type="text/javascript" src='/js/LoginForm.js'></script> 
</head>



<body>


  <div class="login-page">

    <div class="form">

     <form method="POST" class="register-form" id="register-form" action = "/home?command=Register">

            <input type="text" placeholder="name" id="name_register" name = "name"/>
            <div id="name_error" style="color: red"></div>

            <input type="text" placeholder="surname" id="surname" name = "surname"/>
            <div id="surname_error" style="color: red"></div>

            <input type="text" placeholder="email address" id="email_register" name = "email"/>s
            <div id="email_error" style="color:red"></div>

            <input type="password" placeholder="password" id="register_password" name = "password"/>
            <div id="password_error" style="color: red"></div>

            <input type="password" placeholder="repeat password" id="repeat_password" name="repeat_password">

            <button onclick = "return registerFormValidator();">create</button>
            <p class="message">Already registered? <a href="#" onclick="switchToLoginForm()">Login</a></p>
          </form>

          <form   method="POST"  class="login-form" id="login-form" action = "/home?command=Login">
            <input type="text" placeholder="email" id="email" name="email" />
            <input type="password" placeholder="password" id="password" name="password">
            <button>login</button>
            <p class="message">Not registered? <a href="#" onclick="switchToRegisterForm()">Create an account</a></p>
          </form>

    </div>

</body>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html" charset="utf-8">
  <link type="text/css" rel="stylesheet" href=<c:url value="/styles/login_style.css" />
</head>

<body>

  <div class="login-page">

    <div class="form">
      <form class="register-form">
        <input type="text" placeholder="name" />
        <input type="password" placeholder="password" />
        <input type="text" placeholder="email address" />
        <button>create</button>
        <p class="message">Already registered? <a href="#">Sign In</a></p>
      </form>

      <form method = "POST" class="login-form" action = "LoginController">
        <input type="text" placeholder="email" id = "email" name = "email"/>
        <input type="password" placeholder="password" id = "password" name = "password">
        <button>login</button>
        <p class="message">Not registered? <a href="#">Create an account</a></p>
      </form>
    </div>
  </div>

</body>

</html>
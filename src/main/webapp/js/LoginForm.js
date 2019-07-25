function switchToRegisterForm() {
   document.getElementById("login-form").style.display = "none"
   document.getElementById("login-form").reset()
   document.getElementById("register-form").style.display = "block"
}

function switchToLoginForm() {
   document.getElementById("register-form").style.display = "none"
   document.getElementById("register-form").reset();
   clearRegisterErrors()
   document.getElementById("login-form").style.display = "block"
}

function validateEmail(email) {
   var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   return re.test(email);
}

function registerFormValidator() {
   var email = document.getElementById("email_register").value;
   var password = document.getElementById("register_password").value;
   var repeatedPassword = document.getElementById("repeat_password").value;
   var name = document.getElementById("name_register").value;
   var surname = document.getElementById("surname").value;

   //errors elements
   clearRegisterErrors();
   var nameError = document.getElementById("name_error");
   var emailError = document.getElementById("email_error");
   var passwordError = document.getElementById("password_error");
   var surnameError = document.getElementById("surname_error");


   if (name.length == 0 || name.length >= 50) {
      nameError.textContent = "Name is required and should have less then 51 chars.";
      return false;
   }

   if (surname.length <= 1 || surname.length >= 70) {
      surnameError.textContent = "Surname is required ans sould have less then 70 chars."
      return false;
   }


   if (!validateEmail(email)) {
      emailError.textContent = "Email is not correct.";
      return false;
   }

   if (password.length <= 3 || password.length >= 15) {
      passwordError.textContent = "Password should have at least 3 symbols and less then 15";
      return false;
   }

   if (password.length != repeatedPassword.length) {
      passwordError.textContent = "Password are not equals.";
      return false;
   }

   return true;
}

function clearRegisterErrors() {
   document.getElementById("name_error").textContent = "";
   document.getElementById("email_error").textContent = "";
   document.getElementById("password_error").textContent = "";
   document.getElementById("surname_error").textContent = "";
}


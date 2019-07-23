package controller;

import data.business.User;
import data.business.UserRole;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserService;

@WebServlet(name = "RegisterController", urlPatterns = "/RegisterController")
public class RegisterController extends HttpServlet {
  UserService userService = new UserService();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    userService.registerUser(buildUserFromInput(req));
  }


  private User buildUserFromInput(HttpServletRequest request) {
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String password = request.getParameter("password");
    String email = request.getParameter("email");


    System.out.println(new User.Builder()
            .setUserName(name)
            .setUserSurname(surname)
            .setUserPassword(password)
            .setUserEmail(email)
            .setUserRole(new UserRole("USER"))
            .build());

    return new User.Builder()
            .setUserName(name)
            .setUserSurname(surname)
            .setUserPassword(password)
            .setUserEmail(email)
            .setUserRole(new UserRole("USER"))
            .build();
  }
}

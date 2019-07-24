package controller.commands;

import data.business.User;
import data.business.UserRole;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import service.UserService;

public class RegisterCommand extends FrontCommand {

  UserService userService = new UserService();

  @Override
  public void process() throws ServletException, IOException {
    userService.registerUser(buildUserFromInput(request));
    forward("user_info");
  }

  private User buildUserFromInput(HttpServletRequest request) {
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String password = request.getParameter("password");
    String email = request.getParameter("email");

    return new User.builder()
            .setUserName(name)
            .setUserSurname(surname)
            .setUserPassword(password)
            .setUserEmail(email)
            .setUserRole(new UserRole("USER"))
            .build();
  }
}
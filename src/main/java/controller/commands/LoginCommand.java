package controller.commands;

import data.business.User;
import java.io.IOException;
import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

public class LoginCommand extends FrontCommand {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  private UserService userService = new UserService();

  @Override
  public void process() throws ServletException, IOException {
    logger.debug(request.getQueryString());
    logger.debug(request.getCharacterEncoding());
    System.out.println(request.getQueryString());

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = userService.checkAndGetUser(email, password);

    if (user != null) {
      if (user.getUserRole().getRoleName().equals("ADMIN")) forward("admin_info");
      else forward("user_info");
    } else {

    }

  }
}

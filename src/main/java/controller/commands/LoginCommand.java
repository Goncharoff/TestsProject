package controller.commands;

import data.business.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ServiceFactory;
import service.UserService;

public class LoginCommand extends FrontCommand {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private UserService userService = ServiceFactory.getUserService();

  @Override
  public void process() throws ServletException, IOException {
    HttpSession session = request.getSession(true);

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = userService.checkAndGetUser(email, password);

    if (user != null) {
      session.setAttribute("username", user.getId());

      if (user.getUserRole().getRoleName().equals("ADMIN")) redirect("/admin_info");
      else redirect("/user_info");

    } else {
      logger.error("Can not find user with email = {}", email);
    }

  }

}


package controller.commands;

import data.business.User;
import java.io.IOException;
import javax.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.repository_implementations.UserRepositoryImpl;
import service.UserService;

public class LoginCommand extends FrontCommand {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  private UserService userService = new UserService();

  @Override
  public void process() throws ServletException, IOException {

    UserRepositoryImpl userRepository = new UserRepositoryImpl();

    logger.info("This is what happens now");
    logger.info(userRepository.selectUserAndStatistic(1).get().toString());

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


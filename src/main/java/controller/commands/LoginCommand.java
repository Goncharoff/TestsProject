package controller.commands;

import data.business.User;
import data.response.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ServiceFactory;
import service.UserService;
import utils.UserNotFoundException;

public class LoginCommand extends FrontCommand {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User inUser = convertStringToJsonObject(User.class).orElseThrow(UserNotFoundException::new);
        User user = userService.checkAndGetUser(inUser.getUserEmail(), inUser.getUserPassword());

        if (user != null) {
            session.setAttribute("username", user.getId());
            new ResponseWrapper<>(user, response, 200);


            if (user.getUserRole().getRoleName().equals("ADMIN")) {
                redirect("/admin_info");
            } else {
                redirect(String.format("/user_info?user=%d", user.getId()));

            }

        } else {
            logger.error("Can not find user with email = {}", inUser.getUserEmail());
        }

    }

}


package controller.commands;

import data.business.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import service.ServiceFactory;
import service.UserService;
import utils.UserNotFoundException;

public class LoginCommand extends FrontCommand {
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User inUser = convertStringToJsonObject(User.class).orElseThrow(UserNotFoundException::new);
        User user = userService.checkAndGetUser(inUser.getUserEmail(), inUser.getUserPassword());
        response.setContentType("application/json");

        if (user != null) {
            session.setAttribute("username", user.getId());

            if (user.getUserRole().getRoleName().equals("ADMIN")) {
                response.getWriter().write("{\"redirect\": " + "\"" + context.getContextPath() + "/admin_info\"}");
            } else {
                response.getWriter().write("{\"redirect\": " + "\"" + request.getRequestURL() + "/user_info\"}");
            }

        } else {
            logger.error("Can not find user with email = {}", inUser.getUserEmail());
        }

    }

}


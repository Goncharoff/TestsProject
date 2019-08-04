package controller.commands;

import data.business.User;
import data.business.UserRole;
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
        logger.info("Got user = " + inUser);
        User user = userService.checkAndGetUser(inUser.getUserEmail(), inUser.getUserPassword());
        response.setContentType("application/json");
        response.getWriter().write("{\"redirect\": \"http://localhost:9050/user_info.jsp\"}");
        if (user != null) {
            session.setAttribute("username", user.getId());
           // new ResponseWrapper<>(user, response, 200);


            if (user.getUserRole().getRoleName().equals("ADMIN")) {
                redirect("/admin_info");
            } else {
                //response.sendRedirect("/jsp/user_info.jsp");
               //redirect("/jsp/user_info.jsp");

            }

        } else {
            logger.error("Can not find user with email = {}", inUser.getUserEmail());
        }

    }

}


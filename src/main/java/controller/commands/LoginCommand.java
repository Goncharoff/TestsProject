package controller.commands;

import data.business.Role;
import data.business.User;
import data.response.RedirectResponse;
import data.response.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import service.ServiceFactory;
import service.UserService;
import utils.JsonMappingException;

/**
 * Command for login user.
 */
public class LoginCommand extends FrontCommand {
    private final UserService userService = ServiceFactory.getUserService();

    /**
     * Checks user email and password and login user. If ADMIN redirects to admin_profile,
     * if user - to user profile.
     *
     * @throws JsonMappingException - if input request params are not invalid for entity to map.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        User inputUser = convertRequestToJsonObject(User.class).orElseThrow(
                () -> new JsonMappingException("Can not map object")
        );

        User user = userService.checkAndGetUser(inputUser.getUserEmail(), inputUser.getUserPassword());
        session.setAttribute("userId", user.getId());
        String redirectUrl = user.getUserRole() == Role.ADMIN ? "/admin_info" : "/user_info";

        new ResponseWrapper<>(new RedirectResponse(request.getContextPath() + redirectUrl), response);
    }

}


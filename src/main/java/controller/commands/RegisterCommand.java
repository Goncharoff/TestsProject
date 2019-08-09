package controller.commands;

import data.business.User;
import data.response.RedirectResponse;
import data.response.ResponseWrapper;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import service.ServiceFactory;
import service.UserService;
import utils.JsonMappingException;

/**
 * Command for register user.
 */
public class RegisterCommand extends FrontCommand {
    private static final String USER_JSP = "user_info";
    private UserService userService = ServiceFactory.getUserService();

    /**
     * Gets user credentials from user form and redirect it to login page.
     *
     * @throws JsonMappingException if params can not be mapped to user.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process() throws ServletException, IOException {
        User inputUser = convertRequestToJsonObject(User.class).orElseThrow(
                () -> new JsonMappingException("Can't map user input params to such json")
        );
        try {
            userService.registerUser(inputUser);

            new ResponseWrapper<>(new RedirectResponse(request.getContextPath() + "/user_info"), response);
        } catch (SQLIntegrityConstraintViolationException ex) {
            logger.error("User already exist");
        }
    }

}

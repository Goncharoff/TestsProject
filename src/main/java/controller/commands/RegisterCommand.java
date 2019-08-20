package controller.commands;

import data.business.User;
import data.response.ErrorResponse;
import data.response.RedirectResponse;
import data.response.ResponseWrapper;
import error.UserAlreadyExist;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.validation.Validator;
import service.ServiceFactory;
import service.UserService;
import error.JsonMappingException;

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


        userService.validateUserParams(inputUser);

        try {
            userService.registerUser(inputUser);
        } catch (UserAlreadyExist ex) {
            new ResponseWrapper<>(new ErrorResponse(ex.getMessage()), response, 422);
        }


        new ResponseWrapper<>(new RedirectResponse(request.getContextPath() + "/user_info"), response);

    }

}

package controller.commands;

import data.business.User;
import data.response.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import service.ServiceFactory;
import service.UserService;

/*
    Getting info for user profile;
 */
public class UserProfileCommand extends FrontCommand {

    private UserService userService = ServiceFactory.getUserService();

    /**
     * Get id of user from session and then get his info by it.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process() throws ServletException, IOException {
        User userStatistics = userService.selectUserWithStatisticById(
                (long) request.getSession().getAttribute("userId")
        );

        new ResponseWrapper<>(userStatistics, response, 200);
    }
}

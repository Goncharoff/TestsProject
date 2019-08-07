package controller.commands;

import data.business.User;
import data.responses.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import service.ServiceFactory;
import service.UserService;

public class UserProfileCommand extends FrontCommand {

    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void process() throws ServletException, IOException {
        User userStatistics = userService.selectUserWithStatisticById(
                (long) request.getSession().getAttribute("username")
        );

        new ResponseWrapper<>(userStatistics, response, 200);
    }
}

package controller.commands;

import data.business.User;
import data.response.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import service.ServiceFactory;
import service.UserService;

public class UserProfileCommand extends FrontCommand {

    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void process() throws ServletException, IOException {
        User userStatistics = userService.selectUserWithStatisticById(Integer.parseInt(request.getParameter("user_id")));
        new ResponseWrapper<>(userStatistics, response, 200);
    }
}

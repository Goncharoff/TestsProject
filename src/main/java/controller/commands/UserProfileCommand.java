package controller.commands;

import data.business.UserStatistic;
import data.response.ResponseWrapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import service.UserStatisticService;

public class UserProfileCommand extends FrontCommand {

    private UserStatisticService userStatisticService = new UserStatisticService();

    @Override
    public void process() throws ServletException, IOException {
        List<UserStatistic> userStatistics = userStatisticService.getByUserId(Integer.parseInt(request.getParameter("user")));
        new ResponseWrapper<>(userStatistics, response, 200);

    }
}

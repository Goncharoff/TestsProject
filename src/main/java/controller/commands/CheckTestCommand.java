package controller.commands;

import data.business.UserStatistic;
import data.request.TestResult;
import data.response.ErrorResponse;
import data.response.ResponseWrapper;
import error.JsonMappingException;
import service.ServiceFactory;
import service.TestItemService;
import service.UserStatisticService;

import javax.servlet.ServletException;
import java.io.IOException;

public class CheckTestCommand extends FrontCommand {
    private final UserStatisticService userStatisticService = ServiceFactory.getUserStatisticService();
    private final TestItemService testItemService = ServiceFactory.getTestItemService();

    @Override
    public void process() throws ServletException, IOException {
        super.process();

        TestResult testResult = convertRequestToJsonObject(TestResult.class).orElseThrow(
                () -> new JsonMappingException("Can not map object")
        );

        Long userId = (Long) request.getSession().getAttribute("userId");

        if (userId == null) {
            new ResponseWrapper<>(
                    new ErrorResponse("Can not find user with such id"),
                    response,
                    401);
        } else {

            UserStatistic userStatistic = new UserStatistic.builder()
                    .setTestName(testItemService.getTestItemNameById(testResult.getTestId()))
                    .setUserId(userId)
                    .setCorrectAnswers(testResult.getCorrects())
                    .setPassedAnswers(testResult.getTotal())
                    .build();

            userStatisticService.insertUserStatisticByUserId(userId, userStatistic);

            new ResponseWrapper<>(userStatistic, response, 200);
        }
    }
}

package controller.commands;

import java.io.IOException;
import javax.servlet.ServletException;

import data.response.ErrorResponse;
import data.response.ResponseWrapper;
import service.QuestionService;
import service.ServiceFactory;

public class TestSessionCommand extends FrontCommand {
    private final QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public void process() throws ServletException, IOException {
        try {
            int testId = Integer.parseInt(request.getParameter("testId"));
            new ResponseWrapper<>(questionService.getQuestionAnAnswersByTestItemId(testId), response, 200);
        } catch (NumberFormatException ex) {
            logger.error("Can not parse id.");
            new ResponseWrapper<>(
                    new ErrorResponse("Can not find test with such id"),
                    response,
                    401);
        }
    }
}

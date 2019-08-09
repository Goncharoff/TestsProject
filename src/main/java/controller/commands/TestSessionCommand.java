package controller.commands;

import data.responses.ResponseWrapper;
import java.io.IOException;
import javax.servlet.ServletException;
import repository.QuestionRepository;
import repository.RepositoryFactory;
import service.QuestionService;
import service.ServiceFactory;

public class TestSessionCommand extends FrontCommand {
    private final QuestionService questionService = ServiceFactory.getQuestionService();
    QuestionRepository questionRepository = RepositoryFactory.getQuestionRepository();

    @Override
    public void process() throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("test"));
        new ResponseWrapper<>(questionService.getQuestionAnAnswersByTestItemId(testId), response, 200);
        redirect("tests/test_session/" + testId);
    }
}

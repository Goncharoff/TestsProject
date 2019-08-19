package controller.commands;

import data.request.TestItemsResult;
import data.response.ResponseWrapper;
import error.JsonMappingException;
import java.io.IOException;
import javax.servlet.ServletException;
import service.QuestionService;
import service.ServiceFactory;

public class CheckTestCommand extends FrontCommand {
    private final QuestionService questionService = ServiceFactory.getQuestionService();

    @Override
    public void process() throws ServletException, IOException {
        super.process();

        TestItemsResult testItemsResult = convertRequestToJsonObject(TestItemsResult.class).orElseThrow(
                () -> new JsonMappingException("Can not map object")
        );

        logger.info(testItemsResult.getTestItemId() + " and it's result" + "\n" + testItemsResult.getTestItemsResult());

        new ResponseWrapper<>(testItemsResult, response, 200);
    }
}

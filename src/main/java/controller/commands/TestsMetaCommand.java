package controller.commands;

import data.business.TestItem;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

import data.response.ResponseWrapper;
import service.ServiceFactory;
import service.TestItemService;

public class TestsMetaCommand extends FrontCommand {
    private final TestItemService testItemService = ServiceFactory.getTestItemService();

    @Override
    public void process() throws ServletException, IOException {
        super.process();

        List<TestItem> testItemList = testItemService.getAllTestItems();
        new ResponseWrapper<>(testItemList, response, 200);
    }
}

package controller.commands;

import data.business.TestItem;
import data.responses.ResponseWrapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import service.ServiceFactory;
import service.TestItemService;

public class TestsMetaCommand extends FrontCommand {
    private final TestItemService testItemService = ServiceFactory.getTestItemService();

    @Override
    public void process() throws ServletException, IOException {
        List<TestItem> testItemList = testItemService.getAllTestItemsWithPagination();
        new ResponseWrapper<>(testItemList, response, 200);
    }
}

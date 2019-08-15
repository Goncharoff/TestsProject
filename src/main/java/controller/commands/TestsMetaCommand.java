package controller.commands;

import data.business.TestItem;
import data.request.PaginationRequest;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

import data.response.ResponseWrapper;
import service.ServiceFactory;
import service.TestItemService;

public class TestsMetaCommand extends FrontCommand {
    private final TestItemService testItemService = ServiceFactory.getTestItemService();
    private static final int STANDART_PAGINATION_PARAM = 1;
    private static final int STANDART_OFFSET_PARAM = 5;


    @Override
    public void process() throws ServletException, IOException {
        super.process();

        List<TestItem> testItems;

        int limit = Integer.parseInt(request.getParameter("limit"));
        int offset = Integer.parseInt(request.getParameter("offset"));

        if (limit == 0 || offset == 0) {
            testItems = testItemService.getAllTestItemsWithPagination(STANDART_PAGINATION_PARAM, STANDART_OFFSET_PARAM);
        } else {

            testItems = testItemService.getAllTestItemsWithPagination(limit, offset);
        }


        new ResponseWrapper<>(testItems, response, 200);
    }
}

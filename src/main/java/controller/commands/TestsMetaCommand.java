package controller.commands;


import java.io.IOException;
import javax.servlet.ServletException;

import data.response.ResponseWrapper;
import data.response.TestItemsPaginationResponse;
import service.ServiceFactory;
import service.TestItemService;

public class TestsMetaCommand extends FrontCommand {
    private final TestItemService testItemService = ServiceFactory.getTestItemService();
    private static final int STANDART_PAGINATION_PARAM = 3;
    private static final int STANDART_OFFSET_PARAM = 5;


    @Override
    public void process() throws ServletException, IOException {
        super.process();

        int limit = STANDART_PAGINATION_PARAM;
        int offset = STANDART_OFFSET_PARAM;

        try {
            limit = Integer.parseInt(request.getParameter("limit"));
            offset = Integer.parseInt(request.getParameter("offset"));
        } catch (NumberFormatException ex) {
            logger.error("Can't parse parameters.");
        }

        TestItemsPaginationResponse testItems = testItemService.getAllTestITemsWithPaginationResponse(offset, limit);

        new ResponseWrapper<>(testItems, response, 200);
    }
}

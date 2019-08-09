package service;

import data.business.TestItem;
import java.util.List;
import repository.RepositoryFactory;
import repository.TestItemRepository;

public class TestItemService {
    private TestItemRepository testItemRepository = RepositoryFactory.getTestItemRepository();
    private static final int STANDART_NUMBER_TESTS_ON_PAGE = 5;
    private static final int FIRST_PAGE = 1;

    public List<TestItem> getAllTestItems() {
        return testItemRepository.getAllTestItems();
    }

    public List<TestItem> getAllTestItemsWithPagination() {
        return testItemRepository.getPagingTestItems(FIRST_PAGE, STANDART_NUMBER_TESTS_ON_PAGE);
    }

    public List<TestItem> getAllTestItemsWithPagination(int pageNumber, int pageSize) {
        return testItemRepository.getPagingTestItems(pageNumber, pageSize);
    }
}

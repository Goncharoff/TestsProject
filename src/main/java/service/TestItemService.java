package service;

import data.business.TestItem;

import java.util.List;

import data.response.TestItemsPaginationResponse;
import repository.implementation.RepositoryFactory;
import repository.TestItemRepository;

public class TestItemService {
    private TestItemRepository testItemRepository = RepositoryFactory.getTestItemRepository();

    public List<TestItem> getAllTestItems() {
        return testItemRepository.getAllTestItems();
    }

    public List<TestItem> getAllTestItemsWithPagination(int offset, int limit) {
        return testItemRepository.getPagingTestItems(offset, limit);
    }

    public String getTestItemNameById(long id) {
        return testItemRepository.getTestItemNameById(id).orElseThrow(IllegalArgumentException::new);
    }

    public TestItemsPaginationResponse getAllTestITemsWithPaginationResponse(int offset, int limit) {
        List<TestItem> testItems = testItemRepository.getPagingTestItems(offset, limit);
        long totalNumber = testItemRepository.getNumberOfTestItems();

        return new TestItemsPaginationResponse(totalNumber, testItems);
    }
}

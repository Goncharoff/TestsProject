package service;

import data.business.TestItem;
import java.util.List;
import repository.TestItemRepository;
import repository.repository_implementations.TestItemRepositoryImpl;

public class TestItemService {
  private TestItemRepository testItemRepository = new TestItemRepositoryImpl();

  public List<TestItem> getAllTestItems() {
    return testItemRepository.getAllTestItems();
  }

  public List<TestItem> getAllTestItemsWithPagination(int pageNumber, int pageSize) {
    return testItemRepository.getPagingTestItems(pageNumber, pageSize);
  }
}

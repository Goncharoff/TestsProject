package service;

import data.business.TestItem;
import java.util.List;
import repository.RepositoryFactory;
import repository.TestItemRepository;

 class TestItemService {
  private TestItemRepository testItemRepository = RepositoryFactory.getTestItemRepository();

  public List<TestItem> getAllTestItems() {
    return testItemRepository.getAllTestItems();
  }

  public List<TestItem> getAllTestItemsWithPagination(int pageNumber, int pageSize) {
    return testItemRepository.getPagingTestItems(pageNumber, pageSize);
  }
}

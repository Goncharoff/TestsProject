package repository;

import data.business.TestItem;

import java.util.List;
import java.util.Optional;

public interface TestItemRepository {
    List<TestItem> getAllTestItems();

    List<TestItem> getPagingTestItems(int pageNumber, int pageSize);

    Optional<String> getTestItemNameById(long id);

    long getNumberOfTestItems();
}

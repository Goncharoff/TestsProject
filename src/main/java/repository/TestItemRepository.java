package repository;

        import data.business.TestItem;
        import java.util.List;

public interface TestItemRepository {
  List<TestItem> getAllTestItems();

  List<TestItem> getPagingTestItems(int pageNumber, int pageSize);

  long getNumberOfTestItems();
}

package repository.repository_implementations;

import data.ConnectionPool;
import data.business.Language;
import data.business.TestItem;
import data.quires.TestItemQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.TestItemRepository;

public class TestItemRepositoryImpl implements TestItemRepository {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public List<TestItem> getAllTestItems() {
    List<TestItem> resultSet = new ArrayList<>();

    try (Connection connection = ConnectionPool.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(TestItemQueries.SELECT_ALL_TEST_ITEMS.getQUERY());
         ResultSet rs = preparedStatement.executeQuery()) {

      while (rs.next()) {
        resultSet.add(new TestItem.builder()
                .setId(rs.getInt("test_item_id"))
                .setTheme(rs.getString("theme"))
                .setName(rs.getString("name"))
                .setLanguage(new Language(rs.getInt("language_id"), rs.getString("language_name")))
                .setDesctiption(rs.getString("description"))
                .build());
      }

    } catch (SQLException e) {
      logger.error("Error during selecting tet items ", e);
    }

    return resultSet;
  }


}

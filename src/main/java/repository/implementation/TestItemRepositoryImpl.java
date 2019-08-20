package repository.implementation;

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
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.TestItemRepository;

public class TestItemRepositoryImpl implements TestItemRepository {
    private final static Logger logger = LoggerFactory.getLogger(TestItemRepositoryImpl.class);

    //should be instanced using factory
    TestItemRepositoryImpl() {
    }

    @Override
    public List<TestItem> getAllTestItems() {
        List<TestItem> resultSet = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TestItemQueries.SELECT_ALL_TEST_ITEMS.getQUERY());
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                resultSet.add(buildTestItemFromRs(rs));
            }

        } catch (SQLException e) {
            logger.error("Error during selecting test items ", e);
        }

        return resultSet;
    }

    @Override
    public List<TestItem> getPagingTestItems(int pageNumber, int pageSize) {

        if (pageNumber < 0 && pageSize < 0) {
            throw new IllegalArgumentException("Negative parameters in pagination page number = " + pageNumber +
                    " page size = " + pageSize);
        }

        String selectQueryWithPagination = TestItemQueries.SELECT_ALL_TEST_ITEMS.getQUERY() +
                " LIMIT " +
                (pageNumber - 1) * pageSize +
                " , " +
                pageSize;

        List<TestItem> resultSet = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQueryWithPagination);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                resultSet.add(buildTestItemFromRs(rs));
            }

        } catch (SQLException e) {
            logger.error("Error during selecting test items with pagination", e);
        }

        return resultSet;
    }

    @Override
    public long getNumberOfTestItems() {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TestItemQueries.SELECT_NUMBER_OF_ITEMS.getQUERY());
             ResultSet rs = preparedStatement.executeQuery()) {
            rs.next();

            return rs.getLong("cnt");
        } catch (SQLException ex) {
            logger.error("Can not get number of items from test items table", ex);
        }

        return 0;
    }

    @Override
    public Optional<String> getTestItemNameById(long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(TestItemQueries.SELECT_TEST_NAME_BY_ID.getQUERY());
             ResultSet rs = getPSForTestNameItem(preparedStatement, id).executeQuery()) {
            rs.next();

            return Optional.of(rs.getString("name"));
        } catch (SQLException ex) {
            logger.error("Can not get test name with id = " + id);
        }


        return Optional.empty();
    }

    private TestItem buildTestItemFromRs(ResultSet rs) throws SQLException {

        return new TestItem.builder()
                .setId(rs.getInt("test_item_id"))
                .setTheme(rs.getString("theme"))
                .setName(rs.getString("name"))
                .setDuration(rs.getLong("duration"))
                .setLanguage(Language.provideLanguageByCode(rs.getInt("language_id")))
                .setDesctiption(rs.getString("description"))
                .build();
    }

    private PreparedStatement getPSForTestNameItem(PreparedStatement preparedStatement, long id) throws SQLException {
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}

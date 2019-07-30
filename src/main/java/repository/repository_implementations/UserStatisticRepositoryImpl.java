package repository.repository_implementations;

import data.ConnectionPool;
import data.business.UserStatistic;
import data.quires.UserStatisticQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.UserStatisticRepository;

public class UserStatisticRepositoryImpl implements UserStatisticRepository {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public Optional<List<UserStatistic>> selectUserStatisticByUserId(long id) {
    List<UserStatistic> resultUserStatistics = new ArrayList<>();

    try (Connection connection = ConnectionPool.getConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement(UserStatisticQueries.GET_STATISTIC_BY_USER_ID.getQUERY());
         ResultSet rs = getPSForUserStatistic(preparedStatement, id).executeQuery()) {

      UserStatistic.builder userStatisticBuilder = new UserStatistic.builder();

      while (rs.next()) {
        userStatisticBuilder
                .setId(rs.getLong("user_statistic_id"))
                .setTestName(rs.getString("test_name"))
                .setPassedAnswers(rs.getInt("all_questions_passed"))
                .setCorrectAnswers(rs.getInt("correct_answered"))
                .setDateRecorded(rs.getDate("date_recorded"));

        resultUserStatistics.add(userStatisticBuilder.build());
      }

    } catch (SQLException ex) {
      logger.error("Error during getting user statistic by user id ex = ", ex);
    }

    return Optional.of(resultUserStatistics);
  }

  @Override
  public void updateUserStatistic(long id, UserStatistic userStatistic) {
    try (Connection connection = ConnectionPool.getConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement(UserStatisticQueries.UPDATE_STATISTIC_BY_ID.getQUERY())) {

      getPSForUpdateById(preparedStatement, id, userStatistic).executeUpdate();

    } catch (SQLException ex) {
      logger.error("Error during updating data for statistic", ex);
    }
  }

  @Override
  public void insertStatistic(long user_id, UserStatistic userStatistic) {
    try (Connection connection = ConnectionPool.getConnection();
         PreparedStatement preparedStatement =
                 connection.prepareStatement(UserStatisticQueries.INSERT_BY_USER_ID.getQUERY())) {

      getPSForInserting(preparedStatement, user_id, userStatistic).executeUpdate();

    } catch (SQLException ex) {
      logger.error("Error during inserting data for statistic", ex);
    }
  }

  private PreparedStatement getPSForUserStatistic(PreparedStatement preparedStatement, long id) throws SQLException {
    preparedStatement.setLong(1, id);

    return preparedStatement;
  }

  private PreparedStatement getPSForUpdateById(PreparedStatement preparedStatement, long id, UserStatistic userStatistic)
          throws SQLException {
    preparedStatement.setInt(1, userStatistic.getPassedAnswers());
    preparedStatement.setInt(2, userStatistic.getCorrectAnswers());
    preparedStatement.setString(3, userStatistic.getTestName());
    preparedStatement.setLong(4, id);

    return preparedStatement;
  }

  private PreparedStatement getPSForInserting(PreparedStatement preparedStatement, long user_id, UserStatistic userStatistic)
          throws SQLException {
    preparedStatement.setInt(1, userStatistic.getCorrectAnswers());
    preparedStatement.setInt(2, userStatistic.getPassedAnswers());
    preparedStatement.setString(3, userStatistic.getTestName());
    preparedStatement.setLong(4, userStatistic.getUserId());

    return preparedStatement;
  }
}

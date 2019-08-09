package repository.implementation;

import data.ConnectionPool;
import data.PasswordEncoder;
import data.business.User;
import data.business.UserRole;
import data.business.UserStatistic;
import data.quires.UserQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.UserRepository;
import utils.UserNotFoundException;


public class UserRepositoryImpl implements UserRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void registerUser(User user) {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.INSERT_USER.getQUERY())) {

            getPSUpdateUserStatement(preparedStatement, user).executeUpdate();

        } catch (SQLException ex) {
            logger.error("Error during inserting new user", ex);
        }

    }

    @Override
    public Optional<User> selectUserByMailAndPass(String email, String password) {

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_USER_BY_EMAIL.getQUERY());
             ResultSet rs = getPSForUserByEmail(preparedStatement, email).executeQuery()) {

            rs.next();

            boolean isPassCorrect = PasswordEncoder.validatePassword(password, rs.getString("user_password"));

            if (!isPassCorrect) {
                throw new UserNotFoundException("Password is incorrect!");
            }

            User resultUser = new User.builder().setId(rs.getLong("user_id"))
                    .setUserEmail(rs.getString("user_email"))
                    .setUserName(rs.getString("user_name"))
                    .setUserSurname(rs.getString("user_surname"))
                    .setUserRole(new UserRole(rs.getInt("id_user_roles"), rs.getString("role")))
                    .build();


            return Optional.of(resultUser);

        } catch (SQLException ex) {
            logger.error("Error in selecting user process from login ", ex);
        }


        return Optional.empty();
    }

    @Override
    public Optional<User> selectUserAndStatistic(long id) {
        User.builder userBuilder = new User.builder();
        Map<Long, UserStatistic> statisticResultMap = new HashMap<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_USER_AND_STATISTIC_BY_USER_ID.getQUERY());
             ResultSet rs = getPSForUserStatistic(preparedStatement, id).executeQuery()) {


            while (rs.next()) {
                long userId = rs.getLong("user_id");

                userBuilder.setId(userId)
                        .setUserEmail(rs.getString("user_email"))
                        .setUserName(rs.getString("user_name"))
                        .setUserSurname(rs.getString("user_surname"));

                UserStatistic statistic = statisticResultMap.get(userId);

                if (statistic == null) {
                    UserStatistic userStatistic = new UserStatistic.builder()
                            .setId(rs.getLong("user_statistic_id"))
                            .setTestName(rs.getString("test_name"))
                            .setPassedAnswers(rs.getInt("all_questions_passed"))
                            .setCorrectAnswers(rs.getInt("correct_answered"))
                            .setDateRecorded(rs.getDate("date_recorded"))
                            .setUserId(rs.getInt("user_id"))
                            .build();

                    statisticResultMap.put(id, userStatistic);
                }

            }

            userBuilder.setUserStatistic(new ArrayList<>(statisticResultMap.values()));

            return Optional.ofNullable(userBuilder.build());

        } catch (SQLException ex) {
            logger.error("Error during fetching user with statistic: ", ex);
        }

        return Optional.empty();
    }

    @Override
    public List<User> selectAllUsersWithStatistic() {
        List<User> resultSet = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_ALL_USERS_WITH_STATISTIC.getQUERY());
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                long userId = rs.getInt("users.user_id");
                logger.info("Current id = " + userId);
                selectUserAndStatistic(userId).ifPresent(resultSet::add);
            }

        } catch (SQLException ex) {
            logger.error("Errors during getting all users statistic");
        }

        return resultSet;
    }

    @Override
    public Optional<User> selectUserById(long id) {
        User resultUser = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQueries.SELECT_USER_BY_ID.getQUERY());
             ResultSet rs = getPSForUserStatistic(preparedStatement, id).executeQuery()) {

            rs.next();

            resultUser = new User.builder().setId(rs.getLong("user_id"))
                    .setUserEmail(rs.getString("user_email"))
                    .setUserName(rs.getString("user_name"))
                    .setUserSurname(rs.getString("user_surname"))
                    .build();

            return Optional.of(resultUser);

        } catch (SQLException ex) {
            logger.error("Errors during getting all users statistic");

        }


        return Optional.ofNullable(resultUser);
    }


    private PreparedStatement getPSForUserStatistic(PreparedStatement preparedStatement, long id) throws SQLException {
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }

    private PreparedStatement getPSForUserByEmail(PreparedStatement preparedStatement, String email) throws SQLException {
        preparedStatement.setString(1, email);
        return preparedStatement;
    }

    private PreparedStatement getPSUpdateUserStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getUserEmail());
        preparedStatement.setString(2, PasswordEncoder.generatePasswordHash(user.getUserPassword()));
        preparedStatement.setString(3, user.getUserName());
        preparedStatement.setString(4, user.getUserSurname());

        return preparedStatement;
    }
}

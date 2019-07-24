package repository.repository_implementations;

import data.ConnectionPool;
import data.PasswordEncoder;
import data.business.User;
import data.business.UserRole;
import data.quires.UserQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

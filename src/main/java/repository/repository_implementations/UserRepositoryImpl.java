package repository.repository_implementations;

import data.ConnectionPool;
import data.PasswordEncoder;
import data.business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import data.business.UserRole;
import org.apache.commons.dbcp2.BasicDataSource;
import repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void registerUser(User user) {


        try (BasicDataSource basicDataSource = ConnectionPool.getInstance().getDs();
             Connection connection = basicDataSource.getConnection();
             PreparedStatement preparedStatement = getPSUpdateUserStatement(connection, user)) {

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            //log
            ex.printStackTrace();
        }

    }

    @Override
    public Optional<User> selectUserByMailAndPass(String email, String password) {
        String query = "SELECT user.user_id, user.user_email, user.user_name, user.user_surname, user.user_role, " +
                "user_password, role_name as role, id_user_roles " +
                "FROM user LEFT JOIN user_roles AS r ON user.user_role = r.id_user_roles  WHERE user.user_email = ?";

        try (BasicDataSource basicDataSource = ConnectionPool.getInstance().getDs();
             Connection connection = basicDataSource.getConnection();
             ResultSet rs = getPSForUserByEmail(connection, email, query).executeQuery()) {

            rs.next();

            boolean isPassCorrect = PasswordEncoder.validatePassword(password, rs.getString("user_password"));

            if (isPassCorrect) {
                throw new IllegalArgumentException("wrong pass =(");
            }

            User resultUser = new User.builder().setId(rs.getLong("user_id"))
                    .setUserEmail(rs.getString("user_email"))
                    .setUserName(rs.getString("user_name"))
                    .setUserSurname(rs.getString("user_surname"))
                    .setUserRole(new UserRole(rs.getInt("id_user_roles"), rs.getString("role")))
                    .build();


            return Optional.of(resultUser);

        } catch (SQLException ex) {
            //log
            ex.printStackTrace();
        }


        return Optional.empty();
    }

    private PreparedStatement getPSForUserByEmail(Connection connection, String email, String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);

        return preparedStatement;
    }

    private PreparedStatement getPSUpdateUserStatement(Connection connection, User user) throws SQLException {
        String query = "INSERT INTO user (user_email, user_password, " +
                "user_name, user_surname)" +
                "VALUES(?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, user.getUserEmail());
        preparedStatement.setString(2, PasswordEncoder.generatePasswordHash(user.getUserPassword()));
        preparedStatement.setString(3, user.getUserName());
        preparedStatement.setString(4, user.getUserSurname());

        return preparedStatement;
    }
}

package data;

import data.business.User;
import org.apache.commons.dbcp.BasicDataSource;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDB {

    //TODO add mtm
    public static void insert(User user) {
        BasicDataSource basicDataSource = ConnectionPool.getInstance().getDs();
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String query = "INSERT INTO user (user_email, user_password, " +
                "user_name, user_surname)" +
                "VALUES(?, ?, ?, ?)";

        try {
            connection = basicDataSource.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, user.getUserEmail());
            preparedStatement.setString(2, PasswordEncoder.generatePasswordHash(user.getUserPassword()));
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getUserSurname());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            //log
            ex.printStackTrace();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            //log
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(preparedStatement);
            try {
                basicDataSource.close();
            } catch (SQLException ex) {
                //log
                ex.printStackTrace();
            }

        }
    }

    //TODO custom exception for pass
    public static Optional<User> selectUserMailAndPass(String email, String password) {
        BasicDataSource basicDataSource = ConnectionPool.getInstance().getDs();
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String query = "SELECT * FROM user WHERE user_email = ?";

        try {

            connection = basicDataSource.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            rs = preparedStatement.executeQuery();
            rs.next();

            if (!PasswordEncoder.validatePassword(password, rs.getString("user_password"))) {
                throw new IllegalArgumentException("wrong pass =(");
            }

            User resultUser = new User.Builder().setId(rs.getLong("user_id"))
                    .setUserEmail(rs.getString("user_email"))
                    .setUserName(rs.getString("user_name"))
                    .setUserSurname(rs.getString("user_surname"))
                    .build();

            return Optional.of(resultUser);

        } catch (SQLException ex) {
            //log
            ex.printStackTrace();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            //log
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(preparedStatement);
            try {
                basicDataSource.close();
            } catch (SQLException ex) {
                //log
                ex.printStackTrace();
            }

        }

        return Optional.empty();
    }
}

package repository.implementation;

import data.ConnectionPool;
import data.PasswordEncoder;
import data.business.Role;
import data.business.User;
import data.business.UserStatistic;
import data.quires.UserQueries;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
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


            //new UserRole(rs.getInt("id_user_roles"), rs.getString("role")))

            User resultUser = new User.builder().setId(rs.getLong("user_id"))
                    .setUserEmail(rs.getString("user_email"))
                    .setUserName(rs.getString("user_name"))
                    .setUserSurname(rs.getString("user_surname"))
                    .setUserRole(Role.provideRoleFromCode(rs.getInt("id_user_roles")))
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

    private static class PasswordEncoder {

        static String generatePasswordHash(String password) {
            int iterations = 1000;
            char[] chars = password.toCharArray();

            try {
                byte[] salt = getSalt();
                PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
                SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] hash = skf.generateSecret(spec).getEncoded();
                return iterations + ":" + toHex(salt) + ":" + toHex(hash);
            } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
                throw new SecurityException("halp");
                //log
            }

        }

        static boolean validatePassword(String originalPassword, String storedPassword) {
            String[] parts = storedPassword.split(":");
            int iterations = Integer.parseInt(parts[0]);
            int diff = -1;

            try {
                byte[] salt = fromHex(parts[1]);
                byte[] hash = fromHex(parts[2]);
                PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
                SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
                byte[] testHash = skf.generateSecret(spec).getEncoded();
                diff = hash.length ^ testHash.length;
                for (int i = 0; i < hash.length && i < testHash.length; i++) {
                    diff |= hash[i] ^ testHash[i];
                }

            } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
                throw new SecurityException("halp");
                //TODO logs
            }


            return diff == 0;
        }

        private static byte[] getSalt() throws NoSuchAlgorithmException {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return salt;
        }

        private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
            byte[] bytes = new byte[hex.length() / 2];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
            }
            return bytes;
        }

        private static String toHex(byte[] array) throws NoSuchAlgorithmException {
            BigInteger bi = new BigInteger(1, array);
            String hex = bi.toString(16);
            int paddingLength = (array.length * 2) - hex.length();
            if (paddingLength > 0) {
                return String.format("%0" + paddingLength + "d", 0) + hex;
            } else {
                return hex;
            }
        }
    }

}

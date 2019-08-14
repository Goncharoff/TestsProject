package service;

import data.business.User;
import error.UserAlreadyExist;
import error.UserNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import repository.UserRepository;
import repository.implementation.RepositoryFactory;
import utils.Validator;

public class UserService {
    private UserRepository repository = RepositoryFactory.getUserRepository();

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public User checkAndGetUser(String email, String password) {
        return repository.selectUserByMailAndPass(email, password)
                .orElseThrow(UserNotFoundException::new);
    }

    public void registerUser(User user) throws UserAlreadyExist {

        if (repository.checkIfUserWithSuchEmailExist(user.getUserEmail())) {
            throw new UserAlreadyExist("User with email " + user.getUserEmail() + " already exist");
        }

        repository.registerUser(user);
    }

    public User selectUserWithStatisticById(long id) {
        return repository.selectUserAndStatistic(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> selectAllUsersWithStatistic() {
        return repository.selectAllUsersWithStatistic();
    }


    public User validateUserParams(User user) {
        return Validator.of(user).validate(
                User::getUserName,
                name -> (Objects.nonNull(name) && name.length() > 3),
                "Wrong name")
                .validate(
                        User::getUserEmail,
                        email -> (Objects.nonNull(email) && VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()),
                        "Wrong email")
                .validate(
                        User::getUserSurname,
                        surname -> Objects.nonNull(surname) && surname.length() > 3,
                        "Wrong surname")
                .validate(
                        User::getUserPassword,
                        pass -> Objects.nonNull(pass) && pass.length() > 6,
                        "Wrong password")
                .get();
    }

}

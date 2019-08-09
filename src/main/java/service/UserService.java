package service;

import data.business.User;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import repository.UserRepository;
import repository.implementation.RepositoryFactory;
import error.UserNotFoundException;

class UserService{
    private UserRepository repository = RepositoryFactory.getUserRepository();

    public User checkAndGetUser(String email, String password) {
        return repository.selectUserByMailAndPass(email, password)
                .orElseThrow(UserNotFoundException::new);
    }

    public void registerUser(User user) throws SQLIntegrityConstraintViolationException {
        repository.registerUser(user);
    }

    public User selectUserWithStatisticById(long id) {
        return repository.selectUserAndStatistic(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> selectAllUsersWithStatistic() {
        return repository.selectAllUsersWithStatistic();
    }

}

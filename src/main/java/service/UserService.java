package service;

import data.business.User;
import java.util.List;
import repository.UserRepository;
import repository.implementation.UserRepositoryImpl;
import utils.UserNotFoundException;

public class UserService {
  private UserRepository repository = new UserRepositoryImpl();

  public User checkAndGetUser(String email, String password) {
    return repository.selectUserByMailAndPass(email, password)
            .orElseThrow(UserNotFoundException::new);
  }

  public void registerUser(User user) {
    repository.registerUser(user);
  }


  public List<User> selectAllUsersWithStatistic() {
    return repository.selectAllUsersWithStatistic();
  }

}

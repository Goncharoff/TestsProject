package service;

import data.business.User;
import repository.UserRepository;
import repository.repository_implementations.UserRepositoryImpl;
import utils.UserNotFoundException;

public class UserService {
  private UserRepository repository = new UserRepositoryImpl();

  public User checkAndGetUser(String email, String password) {
    return repository.selectUserByMailAndPass(email, password).orElseThrow(UserNotFoundException::new);
  }

  public void registerUser(User user) {
    repository.registerUser(user);
  }
}

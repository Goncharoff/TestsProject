package repository;

import data.business.User;
import java.util.Optional;

public interface UserRepository {

  void registerUser(User user);

  Optional<User> selectUserByMailAndPass(String email, String password);

  Optional<User> selectUserAndStatistic(long id);
}

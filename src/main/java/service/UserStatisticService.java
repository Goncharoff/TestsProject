package service;

import data.business.UserStatistic;
import java.util.List;
import repository.UserStatisticRepository;
import repository.repository_implementations.UserStatisticRepositoryImpl;
import utils.SqlOperationsException;

//TODO custom exception
public class UserStatisticService {
  private UserStatisticRepository userStatisticRepository = new UserStatisticRepositoryImpl();

  public List<UserStatistic> getByUserId(long id) {
    return userStatisticRepository.selectUserStatisticByUserId(id).orElseThrow(SqlOperationsException::new);
  }

  public void updateStatistic(long id, UserStatistic userStatistic) {
    userStatisticRepository.updateUserStatistic(id, userStatistic);
  }

  public void insertUserStatisticByUserId(long user_id, UserStatistic userStatistic) {
    userStatisticRepository.insertStatistic(user_id, userStatistic);
  }

}

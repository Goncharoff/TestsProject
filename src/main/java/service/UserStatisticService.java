package service;

import data.business.UserStatistic;
import java.util.List;
import repository.RepositoryFactory;
import repository.UserStatisticRepository;
import repository.implementation.UserStatisticRepositoryImpl;
import utils.SqlOperationsException;

//TODO custom exception
public class UserStatisticService {
  private UserStatisticRepository userStatisticRepository = RepositoryFactory.getUserStatisticRepository();

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

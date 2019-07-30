package repository;

import data.business.UserStatistic;
import java.util.List;
import java.util.Optional;

public interface UserStatisticRepository {
  Optional<List<UserStatistic>> selectUserStatisticByUserId(long id);

  void updateUserStatistic(long id, UserStatistic userStatistic);

  void insertStatistic(long user_id, UserStatistic userStatistic);
}

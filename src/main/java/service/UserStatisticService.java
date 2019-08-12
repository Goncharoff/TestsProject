package service;

import data.business.UserStatistic;
import error.DatabaseException;
import java.util.List;
import repository.implementation.RepositoryFactory;
import repository.UserStatisticRepository;

//TODO custom exception
public class UserStatisticService {
    private UserStatisticRepository userStatisticRepository = RepositoryFactory.getUserStatisticRepository();

    public List<UserStatistic> getByUserId(long id) {
        return userStatisticRepository.selectUserStatisticByUserId(id).orElseThrow(() -> new DatabaseException("Can not find user with such params"));
    }

    public void updateStatistic(long id, UserStatistic userStatistic) {
        userStatisticRepository.updateUserStatistic(id, userStatistic);
    }

    public void insertUserStatisticByUserId(long user_id, UserStatistic userStatistic) {
        userStatisticRepository.insertStatistic(user_id, userStatistic);
    }

}

package service;

import data.business.User;
import data.responses.AdminInfoResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.RepositoryFactory;
import repository.UserRepository;
import repository.UserStatisticRepository;
import utils.UserNotFoundException;

public class AdminService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository = RepositoryFactory.getUserRepository();
    UserStatisticRepository statisticRepository = RepositoryFactory.getUserStatisticRepository();

    public AdminInfoResponse getAdminInfo(long id) {
        User adminUser = userRepository.selectUserById(id).orElseThrow(UserNotFoundException::new);
        List<User> usersStatistic = userRepository.selectAllUsersWithStatistic();
        return new AdminInfoResponse(adminUser, usersStatistic);
    }
}

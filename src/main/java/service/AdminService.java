package service;

import data.business.User;
import data.response.AdminInfoResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.UserRepository;
import repository.UserStatisticRepository;
import repository.implementation.RepositoryFactory;
import error.UserNotFoundException;

public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private UserRepository userRepository = RepositoryFactory.getUserRepository();
    UserStatisticRepository statisticRepository = RepositoryFactory.getUserStatisticRepository();

    public AdminInfoResponse getAdminInfo(long id) {
        User adminUser = userRepository.selectUserById(id).orElseThrow(UserNotFoundException::new);
        List<User> usersStatistic = userRepository.selectAllUsersWithStatistic();
        return new AdminInfoResponse(adminUser, usersStatistic);
    }


}

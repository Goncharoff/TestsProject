package repository;

import repository.implementation.QuestionRepositoryImpl;
import repository.implementation.TestItemRepositoryImpl;
import repository.implementation.UserRepositoryImpl;
import repository.implementation.UserStatisticRepositoryImpl;

public class RepositoryFactory {

  private RepositoryFactory() {
  }

  public static QuestionRepository getQuestionRepository() {
    return  new QuestionRepositoryImpl();
  }

  public static TestItemRepositoryImpl getTestItemRepository() {
    return new TestItemRepositoryImpl();
  }

  public static UserRepository getUserRepository() {
    return new UserRepositoryImpl();
  }

  public static UserStatisticRepository getUserStatisticRepository() {
    return new UserStatisticRepositoryImpl();
  }

}

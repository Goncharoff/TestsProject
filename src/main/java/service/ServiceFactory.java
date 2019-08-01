package service;

public class ServiceFactory {

  private ServiceFactory() {
  }

  public static QuestionService getQuestionService() {
    return new QuestionService();
  }

  public static TestItemService getTestItemService() {
    return new TestItemService();
  }

  public static UserService getUserService() {
    return new UserService();
  }

  public static UserStatisticService getUserStatisticService() {
    return new UserStatisticService();
  }
}

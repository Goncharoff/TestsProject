package data.quires;

public enum UserStatisticQueries {
  GET_STATISTIC_BY_USER_ID("SELECT user_statistic_id, correct_answered, all_questions_passed, " +
          " test_name, date_recorded " +
          " FROM user_statistic " +
          " WHERE user_id = ?"),

  UPDATE_STATISTIC_BY_ID("UPDATE user_statistic " +
          " SET all_questions_passed = ?," +
          " correct_answered = ?, " +
          " test_name = ?, " +
          " date_recorded = {fn NOW()} " +
          " WHERE user_statistic_id = ?"),

  INSERT_BY_USER_ID("INSERT INTO user_statistic " +
          "(correct_answered, all_questions_passed, test_name, date_recorded, user_id)" +
          " VALUES(?, ?, ?, {fn NOW()}, ?)");

  private String QUERY;

  UserStatisticQueries(String QUERY) {
    this.QUERY = QUERY;
  }

  public String getQUERY() {
    return QUERY;
  }
}

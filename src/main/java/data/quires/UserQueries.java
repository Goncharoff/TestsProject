package data.quires;

public enum UserQueries {
  INSERT_USER("INSERT INTO user (user_email, user_password, user_name, user_surname)"
          + "VALUES(?, ?, ?, ?)"),

  SELECT_USER_BY_EMAIL("SELECT user.user_id, user.user_email, user.user_name, user.user_surname, user.user_role, " +
          "user_password, role_name as role, id_user_roles " +
          "FROM user LEFT JOIN user_roles AS r ON user.user_role = r.id_user_roles  WHERE user.user_email = ?");

  private String QUERY;

  UserQueries(String QUERY) {
    this.QUERY = QUERY;
  }

  public String getQUERY() {
    return QUERY;
  }
}

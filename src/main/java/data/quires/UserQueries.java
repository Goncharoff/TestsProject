package data.quires;

public enum UserQueries {
  INSERT_USER("INSERT INTO users (user_email, user_password, user_name, user_surname)"
          + "VALUES(?, ?, ?, ?)"),

  SELECT_USER_BY_EMAIL("SELECT users.user_id, users.user_email, users.user_name, " +
          "users.user_surname, users.user_role, user_password, role_name as role, id_user_roles " +
          "FROM users LEFT JOIN user_roles AS r ON users.user_role = r.id_user_roles  WHERE users.user_email = ?");

  private String QUERY;

  UserQueries(String QUERY) {
    this.QUERY = QUERY;
  }

  public String getQUERY() {
    return QUERY;
  }
}

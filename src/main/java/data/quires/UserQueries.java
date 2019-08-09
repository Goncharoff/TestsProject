package data.quires;

public enum UserQueries {
    INSERT_USER("INSERT INTO users (user_email, user_password, user_name, user_surname)"
            + "VALUES(?, ?, ?, ?)"),

    SELECT_USER_BY_EMAIL("SELECT users.user_id, users.user_email, users.user_name, " +
            "users.user_surname, users.user_role, user_password, role_name as role, id_user_roles " +
            "FROM users " +
            "LEFT JOIN user_roles AS r ON users.user_role = r.id_user_roles  " +
            "WHERE users.user_email = ?"),

    SELECT_USER_AND_STATISTIC_BY_USER_ID("SELECT users.user_id, users.user_email, users.user_name, " +
            " users.user_surname, user_statistic.user_statistic_id, user_statistic.correct_answered, " +
            " user_statistic.all_questions_passed, user_statistic.test_name, user_statistic.date_recorded" +
            " FROM users " +
            " INNER JOIN user_statistic " +
            " ON users.user_id = user_statistic.user_id" +
            " WHERE users.user_id =  ?"),

    SELECT_ALL_USERS_WITH_STATISTIC("SELECT users.user_id, users.user_email, users.user_name, " +
            " users.user_surname, user_statistic.user_statistic_id, user_statistic.correct_answered, " +
            " user_statistic.all_questions_passed, user_statistic.test_name, user_statistic.date_recorded" +
            " FROM users " +
            " INNER JOIN user_statistic " +
            " ON users.user_id = user_statistic.user_id"),

    SELECT_USER_BY_ID("SELECT user_id, user_email, user_name, " +
            " user_surname FROM users WHERE user_id = ? ");

    private String QUERY;

    UserQueries(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}

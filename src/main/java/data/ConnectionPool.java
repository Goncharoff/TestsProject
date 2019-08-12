package data;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Connection pull for database, using apache DBCP2 framework implements singleton.
 */
public class ConnectionPool {
<<<<<<< HEAD
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tester_app_prod_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "pa44word";
    private static final int CONN_MIN_POOL_SIZE = 5;
    private static final int CONN_MAX_POOL_SIZE = 10;
    private static final int MAX_NUMBER_OF_PREPARED_STATEMENTS = 100;

    private BasicDataSource ds = new BasicDataSource();

    private static class DataSourceHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    private ConnectionPool() {
        ds.setUrl(DB_URL);
        ds.setUsername(DB_USER);
        ds.setPassword(DB_PASSWORD);
        ds.setMinIdle(CONN_MIN_POOL_SIZE);
        ds.setMaxIdle(CONN_MAX_POOL_SIZE);
        ds.setMaxOpenPreparedStatements(MAX_NUMBER_OF_PREPARED_STATEMENTS);
        ds.setDriverClassName(DRIVER_CLASS_NAME);
    }

    public static ConnectionPool getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    public BasicDataSource getDs() {
        return ds;
    }

    public void setDs(BasicDataSource ds) {
        this.ds = ds;
    }
=======
  private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/tester_app_prod_db?serverTimezone=UTC";
  private static final String DB_USER = "admin";
  private static final String DB_PASSWORD = "pa44word";
  private static final int CONN_MIN_POOL_SIZE = 5;
  private static final int CONN_MAX_POOL_SIZE = 10;
  private static final int MAX_NUMBER_OF_PREPARED_STATEMENTS = 100;

  private static BasicDataSource ds = new BasicDataSource();


  /**
   * Get instance of connection pool.
   */
  private static class DataSourceHolder {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
  }

  private ConnectionPool(){}

  static {
    ds.setUrl(DB_URL);
    ds.setUsername(DB_USER);
    ds.setPassword(DB_PASSWORD);
    ds.setMinIdle(CONN_MIN_POOL_SIZE);
    ds.setMaxIdle(CONN_MAX_POOL_SIZE);
    ds.setMaxOpenPreparedStatements(MAX_NUMBER_OF_PREPARED_STATEMENTS);
    ds.setDriverClassName(DRIVER_CLASS_NAME);

  }

  public static ConnectionPool getInstance() {
    return DataSourceHolder.INSTANCE;
  }

  public static BasicDataSource getDs() {
    return ds;
  }

  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }

>>>>>>> development
}

package error;

public class SqlOperationsException extends RuntimeException {

  public SqlOperationsException() {
  }

  public SqlOperationsException(String message) {
    super(message);
  }

}

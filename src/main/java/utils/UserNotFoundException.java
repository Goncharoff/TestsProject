package utils;

public class UserNotFoundException extends RuntimeException {


  public UserNotFoundException() {
    super("Can not find user with such credentials.");
  }

}

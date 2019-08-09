package error;

public class UserNotFoundException extends DatabaseException {

    public UserNotFoundException() {
        super("Can not find user with such credentials.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

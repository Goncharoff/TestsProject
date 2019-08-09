package error;

public class NotFoundRoleException extends DatabaseException {
    public NotFoundRoleException(String message) {
        super(message);
    }
}

package error;

public class NotFoundRoleException extends RuntimeException{

    public NotFoundRoleException(String message) {
        super(message);
    }
}

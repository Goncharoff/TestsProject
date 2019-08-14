package data.response;

import data.business.User;
import java.io.Serializable;

public class UserResponse implements Serializable {
    private final User user;
    private final int successPercent;
    private final int total;

    public UserResponse(User user, int successPercent, int total) {
        this.user = user;
        this.successPercent = successPercent;
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public int getSuccessPercent() {
        return successPercent;
    }

    public int getTotal() {
        return total;
    }
}

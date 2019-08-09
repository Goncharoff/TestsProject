package data.response;

import data.business.User;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Object to map in json for response for admin and all user statistic.
 */
public class AdminInfoResponse implements Serializable {
    private static final long serialVersionUID = 2423278599414419987L;
    private final User adminUser;
    private final List<User> users;


    public AdminInfoResponse(User adminUser, List<User> users) {
        this.adminUser = adminUser;
        this.users = users;
    }

    public AdminInfoResponse() {
        this.adminUser = null;
        this.users = null;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminInfoResponse)) return false;
        AdminInfoResponse that = (AdminInfoResponse) o;
        return Objects.equals(getAdminUser(), that.getAdminUser()) &&
                Objects.equals(getUsers(), that.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAdminUser(), getUsers());
    }

    @Override
    public String toString() {
        return "AdminInfoResponse{" +
                "adminUser=" + adminUser +
                ", users=" + users +
                '}';
    }
}

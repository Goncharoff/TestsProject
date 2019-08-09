package data.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class User implements Serializable {

    private static final long serialVersionUID = -3135896295862517804L;

    private final long id;
    private final String userEmail;
    private final String userPassword;
    private final UserRole userRole;
    private final String userName;
    private final String userSurname;
    private List<UserStatistic> userStatistics;
    private List<TestItem> testItems;

    public User() {
        this.id = 0;
        this.userEmail = null;
        this.userPassword = null;
        this.userRole = null;
        this.userName = null;
        this.userSurname = null;
        this.userStatistics = null;
        this.testItems = null;
    }

    public User(long id, String userEmail, String userPassword, UserRole userRole, String userName, String userSurname, List<UserStatistic> userStatistics) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userStatistics = userStatistics;
    }

    public static class builder {
        private long id;
        private String userEmail;
        private String userPassword;
        private UserRole userRole;
        private String userName;
        private String userSurname;
        private List<UserStatistic> userStatistics = new ArrayList<>();

        public builder setId(long id) {
            this.id = id;
            return this;
        }

        public builder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public builder setUserPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public builder setUserSurname(String userSurname) {
            this.userSurname = userSurname;
            return this;
        }

        public builder setUserRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public builder setUserStatistic(List<UserStatistic> userStatistic) {
            this.userStatistics.addAll(userStatistic);
            return this;
        }

        public User build() {
            return new User(id, userEmail, userPassword, userRole, userName, userSurname, userStatistics);
        }
    }

    public long getId() {
        return id;
    }


    public String getUserEmail() {
        return userEmail;
    }


    public String getUserPassword() {
        return userPassword;
    }


    public UserRole getUserRole() {
        return userRole;
    }


    public String getUserName() {
        return userName;
    }


    public String getUserSurname() {
        return userSurname;
    }

    public List<UserStatistic> getUserStatistics() {
        return userStatistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getUserEmail(), user.getUserEmail()) &&
                Objects.equals(getUserPassword(), user.getUserPassword()) &&
                Objects.equals(getUserRole(), user.getUserRole()) &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getUserSurname(), user.getUserSurname()) &&
                Objects.equals(getUserStatistics(), user.getUserStatistics()) &&
                Objects.equals(testItems, user.testItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserEmail(), getUserPassword(), getUserRole(), getUserName(), getUserSurname(), getUserStatistics(), testItems);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole=" + userRole +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", userStatistic = " + userStatistics +
                '}';
    }
}

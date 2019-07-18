package data.business;

import java.io.Serializable;


public class User implements Serializable {
    private long id;
    private final String userEmail;
    private final String userPassword;
    private UserRole userRole;
    private final String userName;
    private final String userSurname;
    //TODO add tests MtM


    public User(long id, String userEmail, String userPassword, UserRole userRole, String userName, String userSurname) {
        this.id = id;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userRole = userRole;
        this.userName = userName;
        this.userSurname = userSurname;
    }

    public static class Builder {
        private long id;
        private String userEmail;
        private String userPassword;
        private UserRole userRole;
        private String userName;
        private String userSurname;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder setUserPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setUserSurname(String userSurname) {
            this.userSurname = userSurname;
            return this;
        }

        public Builder setUserRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public User build() {
            return new User(id, userEmail, userPassword, userRole, userName, userSurname);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userRole=" + userRole +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                '}';
    }
}

package data.business;

import utils.NotFoundRoleException;

/**
 * Roles for users.
 */
public enum Role {
    ADMIN(1), USER(2);

    private int role;

    Role(int roleName) {
        this.role = roleName;
    }

    public int getRoleAsCode() {
        return role;
    }

    public static Role provideRoleFromCode(int roleName) {
        switch (roleName) {
            case 1:
                return ADMIN;
            case 2:
                return USER;
            default:
                throw new NotFoundRoleException("Can't find such role as " + roleName);
        }
    }

}

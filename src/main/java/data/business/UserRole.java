package data.business;

import java.io.Serializable;
import java.util.Objects;

public class UserRole implements Serializable {
  private static final long serialVersionUID = -7082656882270827005L;

  private long id;
  private String roleName;

  public UserRole(long id, String roleName) {
    this.id = id;
    this.roleName = roleName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public UserRole(String roleName) {
    this.roleName = roleName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserRole)) return false;
    UserRole userRole = (UserRole) o;
    return getId() == userRole.getId() &&
            Objects.equals(getRoleName(), userRole.getRoleName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getRoleName());
  }

  @Override
  public String toString() {
    return "UserRole{" +
            "id=" + id +
            ", roleName='" + roleName + '\'' +
            '}';
  }
}

package data.business;

import java.io.Serializable;
import java.util.Objects;

/**
 * Cross table object for users and tables.
 */
public class UserHaveTest implements Serializable {

  private static final long serialVersionUID = -5356190852010447794L;
  private int userHaveTestId;
  private int userId;
  private int testId;

  public UserHaveTest(int userHaveTestId, int userId, int testId) {
    this.userHaveTestId = userHaveTestId;
    this.userId = userId;
    this.testId = testId;
  }

  public UserHaveTest(int userId, int testId) {
    this.userId = userId;
    this.testId = testId;
  }


  public int getUserHaveTestId() {
    return userHaveTestId;
  }

  public int getUserId() {
    return userId;
  }

  public int getTestId() {
    return testId;
  }

  public void setUserHaveTestId(int userHaveTestId) {
    this.userHaveTestId = userHaveTestId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setTestId(int testId) {
    this.testId = testId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserHaveTest)) return false;
    UserHaveTest that = (UserHaveTest) o;
    return getUserHaveTestId() == that.getUserHaveTestId() &&
            getUserId() == that.getUserId() &&
            getTestId() == that.getTestId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getUserHaveTestId(), getUserId(), getTestId());
  }

  @Override
  public String toString() {
    return "UserHaveTest{" +
            "userHaveTestId=" + userHaveTestId +
            ", userId=" + userId +
            ", testId=" + testId +
            '}';
  }
}

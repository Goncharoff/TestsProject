package data.business;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class UserStatistic implements Serializable {

  private static final long serialVersionUID = 6058204516328858726L;

  private final long id;
  private final int correctAnswers;
  private final int passedAnswers;
  private final String testName;
  private final Date dateRecorded;
  private final long userId;

  public UserStatistic(long id, int numberOfCorrectAnswers, int numberOfAllPassedAnswers, String testName, Date dateRecorded, long userId) {
    this.id = id;
    this.correctAnswers = numberOfCorrectAnswers;
    this.passedAnswers = numberOfAllPassedAnswers;
    this.testName = testName;
    this.dateRecorded = dateRecorded;
    this.userId = userId;
  }

  public static class builder {
    private long id;
    private int correctAnswers;
    private int passedAnswers;
    private String testName;
    private Date dateRecorded;
    private int userId;

    public builder setId(long id) {
      this.id = id;
      return this;
    }

    public builder setCorrectAnswers(int correctAnswers) {
      this.correctAnswers = correctAnswers;
      return this;
    }

    public builder setPassedAnswers(int passedAnswers) {
      this.passedAnswers = passedAnswers;
      return this;
    }

    public builder setTestName(String testName) {
      this.testName = testName;
      return this;
    }

    public builder setDateRecorded(Date dateRecorded) {
      this.dateRecorded = dateRecorded;
      return this;
    }

    public builder setUserId(int userId) {
      this.userId = userId;
      return this;
    }

    public UserStatistic build() {
      return new UserStatistic(id, correctAnswers, passedAnswers, testName, dateRecorded, userId);
    }
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public long getId() {
    return id;
  }

  public int getCorrectAnswers() {
    return correctAnswers;
  }

  public int getPassedAnswers() {
    return passedAnswers;
  }

  public String getTestName() {
    return testName;
  }

  public Date getDateRecorded() {
    return dateRecorded;
  }

  public long getUserId() {
    return userId;
  }

  @Override
  public String toString() {
    return "UserStatistic{" +
            "id=" + id +
            ", correctAnswers=" + correctAnswers +
            ", passedAnswers=" + passedAnswers +
            ", testName='" + testName + '\'' +
            ", dateRecorded=" + dateRecorded +
            ", userId=" + userId +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserStatistic)) return false;
    UserStatistic that = (UserStatistic) o;
    return getId() == that.getId() &&
            getCorrectAnswers() == that.getCorrectAnswers() &&
            getPassedAnswers() == that.getPassedAnswers() &&
            getUserId() == that.getUserId() &&
            Objects.equals(getTestName(), that.getTestName()) &&
            Objects.equals(getDateRecorded(), that.getDateRecorded());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getCorrectAnswers(), getPassedAnswers(), getTestName(), getDateRecorded(), getUserId());
  }
}

package data.business;

import java.io.Serializable;
import java.util.Objects;

/**
 * Database object for answers table.
 */
public class Answer implements Serializable {
  private static final long serialVersionUID = 2991744696751696716L;
  private int id;
  private int questionId;
  private String answer;
  private boolean isCorrect;
  private String errorDescr;

  public Answer(int id, int questionId, String answer, boolean isCorrect, String errorDescr) {
    this.id = id;
    this.questionId = questionId;
    this.answer = answer;
    this.isCorrect = isCorrect;
    this.errorDescr = errorDescr;
  }

  public int getId() {
    return id;
  }

  public int getQuestionId() {
    return questionId;
  }

  public String getAnswer() {
    return answer;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public String getErrorDescr() {
    return errorDescr;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setQuestionId(int questionId) {
    this.questionId = questionId;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }

  public void setErrorDescr(String errorDescr) {
    this.errorDescr = errorDescr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Answer)) return false;
    Answer answer1 = (Answer) o;
    return getId() == answer1.getId() &&
            getQuestionId() == answer1.getQuestionId() &&
            isCorrect() == answer1.isCorrect() &&
            Objects.equals(getAnswer(), answer1.getAnswer()) &&
            Objects.equals(getErrorDescr(), answer1.getErrorDescr());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getQuestionId(), getAnswer(), isCorrect(), getErrorDescr());
  }

  @Override
  public String toString() {
    return "Answer{" +
            "id=" + id +
            ", questionId=" + questionId +
            ", answer='" + answer + '\'' +
            ", isCorrect=" + isCorrect +
            ", errorDescr='" + errorDescr + '\'' +
            '}';
  }
}

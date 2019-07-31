package data.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Question {
  private final int id;
  private final String text;
  private final String url;
  private final int typeId;
  private final int testItemId;
  private final TypeQuestion questionType;
  private List<Answer> answers;

  public Question(int id, String text, String url, int typeId, int testItemId, TypeQuestion questionType, List<Answer> answers) {
    this.id = id;
    this.text = text;
    this.url = url;
    this.typeId = typeId;
    this.testItemId = testItemId;
    this.questionType = questionType;
    this.answers = answers;
  }

  public static class builder {
    private int id;
    private String text;
    private String url;
    private int typeId;
    private int testItemId;
    private TypeQuestion questionType;
    private List<Answer> answers = new ArrayList<>();

    public builder setId(int id) {
      this.id = id;
      return this;
    }

    public builder setText(String text) {
      this.text = text;
      return this;
    }

    public builder setUrl(String url) {
      this.url = url;
      return this;
    }

    public builder setTypeId(int typeId) {
      this.typeId = typeId;
      return this;
    }

    public builder setTestItemId(int testItemId) {
      this.testItemId = testItemId;
      return this;
    }

    public builder setTypeQuestion(TypeQuestion typeQuestion) {
      this.questionType = typeQuestion;
      return this;
    }

    public builder setAnswers(List<Answer> answers) {
      this.answers = answers;
      return this;
    }

    public Question build() {
      return new Question(id, text, url, typeId, testItemId, questionType, answers);
    }
  }

  public int getId() {
    return id;
  }

  public String getText() {
    return text;
  }

  public String getUrl() {
    return url;
  }

  public int getTypeId() {
    return typeId;
  }

  public int getTestItemId() {
    return testItemId;
  }

  public TypeQuestion getQuestionType() {
    return questionType;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Question)) return false;
    Question question = (Question) o;
    return getId() == question.getId() &&
            getTypeId() == question.getTypeId() &&
            getTestItemId() == question.getTestItemId() &&
            Objects.equals(getText(), question.getText()) &&
            Objects.equals(getUrl(), question.getUrl()) &&
            Objects.equals(getQuestionType(), question.getQuestionType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getText(), getUrl(), getTypeId(), getTestItemId(), getQuestionType());
  }

  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", text='" + text + '\'' +
            ", url='" + url + '\'' +
            ", typeId=" + typeId +
            ", restItemsId=" + testItemId +
            ", questionType=" + questionType +
            '}';
  }
}

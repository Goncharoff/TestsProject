package data.business;

import java.util.Objects;

public class Question {
  private int id;
  private String text;
  private String url;
  private int typeId;
  private int restItemsId;
  private TypeQuestion questionType;

  public Question(int id, String text, String url, int typeId, int restItemsId, TypeQuestion questionType) {
    this.id = id;
    this.text = text;
    this.url = url;
    this.typeId = typeId;
    this.restItemsId = restItemsId;
    this.questionType = questionType;
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

  public int getRestItemsId() {
    return restItemsId;
  }

  public TypeQuestion getQuestionType() {
    return questionType;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setTypeId(int typeId) {
    this.typeId = typeId;
  }

  public void setRestItemsId(int restItemsId) {
    this.restItemsId = restItemsId;
  }

  public void setQuestionType(TypeQuestion questionType) {
    this.questionType = questionType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Question)) return false;
    Question question = (Question) o;
    return getId() == question.getId() &&
            getTypeId() == question.getTypeId() &&
            getRestItemsId() == question.getRestItemsId() &&
            Objects.equals(getText(), question.getText()) &&
            Objects.equals(getUrl(), question.getUrl()) &&
            Objects.equals(getQuestionType(), question.getQuestionType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getText(), getUrl(), getTypeId(), getRestItemsId(), getQuestionType());
  }

  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", text='" + text + '\'' +
            ", url='" + url + '\'' +
            ", typeId=" + typeId +
            ", restItemsId=" + restItemsId +
            ", questionType=" + questionType +
            '}';
  }
}

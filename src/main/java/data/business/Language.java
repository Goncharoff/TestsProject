package data.business;

import java.io.Serializable;
import java.util.Objects;

public class Language implements Serializable {
  private static final long serialVersionUID = -888435907627502633L;
  private int id;
  private String languageId;

  public Language(int id, String languageId) {
    this.id = id;
    this.languageId = languageId;
  }

  public int getId() {
    return id;
  }

  public String getLanguageId() {
    return languageId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Language)) return false;
    Language language = (Language) o;
    return getId() == language.getId() &&
            Objects.equals(getLanguageId(), language.getLanguageId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getLanguageId());
  }

  @Override
  public String toString() {
    return "Language{" +
            "id=" + id +
            ", languageId='" + languageId + '\'' +
            '}';
  }
}

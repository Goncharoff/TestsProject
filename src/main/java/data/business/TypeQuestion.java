package data.business;

import java.io.Serializable;
import java.util.Objects;

public class TypeQuestion implements Serializable {
  private static final long serialVersionUID = -7735066126436978815L;
  private int id;
  private String typeName;

  public TypeQuestion(int id, String typeName) {
    this.id = id;
    this.typeName = typeName;
  }

  public int getId() {
    return id;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TypeQuestion)) return false;
    TypeQuestion that = (TypeQuestion) o;
    return getId() == that.getId() &&
            Objects.equals(getTypeName(), that.getTypeName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getTypeName());
  }

  @Override
  public String toString() {
    return "TypeQuestion{" +
            "id=" + id +
            ", typeName='" + typeName + '\'' +
            '}';
  }
}

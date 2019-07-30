package data.business;

public class TypeQuestion {
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
}

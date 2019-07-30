package data.business;

import java.util.Objects;

public class TestItem {

  private final int testItemId;
  private final int languageId;
  private final String name;
  private final String description;
  private final String theme;
  private final long duration;
  private final Language language;

  public TestItem(int testItemId, int languageId, String name, String description, String theme, long duration, Language language) {
    this.testItemId = testItemId;
    this.languageId = languageId;
    this.name = name;
    this.description = description;
    this.theme = theme;
    this.duration = duration;
    this.language = language;
  }

  public static class builder {
    private int testItemId;
    private int languageId;
    private String name;
    private String description;
    private String theme;
    private long duration;
    private Language language;

    public builder setId(int testItemId) {
      this.testItemId = testItemId;
      return this;
    }

    public builder setLanguageId(int languageId) {
      this.languageId = languageId;
      return this;
    }

    public builder setName(String name) {
      this.name = name;
      return this;
    }

    public builder setDesctiption(String description) {
      this.description = description;
      return this;
    }

    public builder setTheme(String theme) {
      this.theme = theme;
      return this;
    }

    public builder setDuration(long duration) {
      this.duration = duration;
      return this;
    }

    public builder setLanguage(Language language) {
      this.language = language;
      return this;
    }

    public TestItem build() {
      return new TestItem(testItemId, languageId, name, description, theme, duration, language);
    }
  }

  public int getTestItemId() {
    return testItemId;
  }

  public int getLanguageId() {
    return languageId;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getTheme() {
    return theme;
  }

  public long getDuration() {
    return duration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TestItem)) return false;
    TestItem testItem = (TestItem) o;
    return getTestItemId() == testItem.getTestItemId() &&
            getLanguageId() == testItem.getLanguageId() &&
            getDuration() == testItem.getDuration() &&
            Objects.equals(getName(), testItem.getName()) &&
            Objects.equals(getDescription(), testItem.getDescription()) &&
            Objects.equals(getTheme(), testItem.getTheme()) &&
            Objects.equals(language, testItem.language);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTestItemId(), getLanguageId(), getName(), getDescription(), getTheme(), getDuration(), language);
  }

  @Override
  public String toString() {
    return "TestItem{" +
            "testItemId=" + testItemId +
            ", languageId=" + languageId +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", theme='" + theme + '\'' +
            ", duration=" + duration +
            ", language=" + language +
            '}';
  }
}

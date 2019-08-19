package data.quires;

public enum TestItemQueries {


  SELECT_ALL_TEST_ITEMS("SELECT test_item_id, name, description, theme, duration, language_name,  test_items.language_id" +
          " FROM test_items " +
          " LEFT JOIN languages ON  test_items.language_id = languages.language_id "),

  SELECT_NUMBER_OF_ITEMS("SELECT COUNT(*) as cnt FROM test_items");

  private String QUERY;

  TestItemQueries(String QUERY) {
    this.QUERY = QUERY;
  }

  public String getQUERY() {
    return QUERY;
  }
}

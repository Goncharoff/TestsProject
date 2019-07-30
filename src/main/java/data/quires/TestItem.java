package data.quires;

public enum  TestItem {
  SELECT_ALL_TEST_ITEMS("SELECT * FROM test_items");

  private String QUERY;

  TestItem(String QUERY) {
    this.QUERY = QUERY;
  }

  public String getQUERY() {
    return QUERY;
  }
}

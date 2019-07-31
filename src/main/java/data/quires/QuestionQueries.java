package data.quires;

public enum QuestionQueries {

  SELECT_ALL_QUESTIONS_BY_TEST_ITEM_ID("SELECT questions.question_id, question_text, question_image_url, " +
          "questions.type_id, questions.test_items_id type_name,  answer_id, answer, isCorrect, error_descr " +
          " FROM questions " +
          " LEFT JOIN type_questions ON questions.question_id = type_questions.type_id " +
          " LEFT JOIN answers ON questions.question_id = answers.question_id " +
          " WHERE questions.test_items_id = ?");

  private String QUERY;

  QuestionQueries(String QUERY) {
    this.QUERY = QUERY;
  }

  public String getQUERY() {
    return QUERY;
  }
}

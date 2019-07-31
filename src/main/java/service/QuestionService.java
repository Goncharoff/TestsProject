package service;

import data.business.Question;
import java.util.List;
import repository.QuestionRepository;
import repository.repository_implementations.QuestionRepositoryImpl;

public class QuestionService {
  private QuestionRepository questionRepository = new QuestionRepositoryImpl();

  public List<Question> getQuestionAnAnswersByTestItemId(int id) {
    return questionRepository.getQuestionsByTestItemId(id);
  }

}

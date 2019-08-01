package service;

import data.business.Question;
import java.util.List;
import repository.QuestionRepository;
import repository.RepositoryFactory;

public class QuestionService {
  private QuestionRepository questionRepository = RepositoryFactory.getQuestionRepository();

  public List<Question> getQuestionAnAnswersByTestItemId(int id) {
    return questionRepository.getQuestionsByTestItemId(id);
  }

}

package service;

import data.business.Question;
import java.util.Set;
import repository.QuestionRepository;
import repository.RepositoryFactory;

public class QuestionService {
  private QuestionRepository questionRepository = RepositoryFactory.getQuestionRepository();

  public Set<Question> getQuestionAnAnswersByTestItemId(int id) {
    return questionRepository.getQuestionsByTestItemId(id);
  }

}

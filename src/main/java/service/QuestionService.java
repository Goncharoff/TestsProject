package service;

import data.business.Question;

import java.util.List;
import java.util.Set;
import repository.QuestionRepository;
import repository.implementation.RepositoryFactory;

public class QuestionService {
  private QuestionRepository questionRepository = RepositoryFactory.getQuestionRepository();

  public List<Question> getQuestionAnAnswersByTestItemId(int id) {
    return questionRepository.getQuestionsByTestItemId(id);
  }

}

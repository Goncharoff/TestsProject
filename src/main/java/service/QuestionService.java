package service;

import data.business.Question;
import repository.QuestionRepository;
import repository.implementation.RepositoryFactory;

import java.util.List;

public class QuestionService {
    private QuestionRepository questionRepository = RepositoryFactory.getQuestionRepository();

    public List<Question> getQuestionAnAnswersByTestItemId(int id) {
        return questionRepository.getQuestionsByTestItemId(id);
    }

}

package repository;

import data.business.Question;
import java.util.List;

public interface QuestionRepository {

  List<Question> getQuestionsByTestItemId(long id);

}

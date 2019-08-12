package repository;

import data.business.Question;
import java.util.List;
import java.util.Set;

public interface QuestionRepository {

  List<Question> getQuestionsByTestItemId(long id);

}

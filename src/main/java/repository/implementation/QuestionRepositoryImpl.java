package repository.implementation;

import data.ConnectionPool;
import data.business.Answer;
import data.business.Question;
import data.business.TypeQuestion;
import data.quires.QuestionQueries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.QuestionRepository;

public class QuestionRepositoryImpl implements QuestionRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //should be instanced using factory
    QuestionRepositoryImpl() {
    }

    @Override
    public List<Question> getQuestionsByTestItemId(long id) {
        List<Question> questions = new ArrayList<>();
        Map<Integer, Answer> answersMap = new HashMap<>();
        Question.builder questionBuilder = new Question.builder();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuestionQueries.SELECT_ALL_QUESTIONS_BY_TEST_ITEM_ID.getQUERY());
             ResultSet rs = getPSForQuestionByItemId(preparedStatement, id).executeQuery()) {

            while (rs.next()) {
                int questionId = rs.getInt("question_id");

                buildQuestionItem(rs, questionBuilder, questionId);
                addAnswersToMap(rs, answersMap, questionId);

                questions.add(questionBuilder.build());
            }

            mapAnswersToQuestions(questions, answersMap);

        } catch (SQLException e) {
            logger.error("error during getting questions and answers by id ", e);
        }


        return questions;
    }


    private void addAnswersToMap(ResultSet rs, Map<Integer, Answer> answersMap, int questionId) throws SQLException {
        int answerId = rs.getInt("answer_id");
        Answer answer = answersMap.get(answerId);

        if (answer == null) {
            answer = new Answer(answerId, questionId, rs.getString("answer"),
                    rs.getBoolean("isCorrect"),
                    rs.getString("error_descr"));

            answersMap.put(answer.getId(), answer);
        }
    }

    private void buildQuestionItem(ResultSet rs, Question.builder questionBuilder, int questionId) throws SQLException {

        questionBuilder.setId(questionId)
                .setText(rs.getString("question_text"))
                .setUrl(rs.getString("question_image_url"))
                .setTypeQuestion(new TypeQuestion(rs.getInt("type_id"), rs.getString("type_name")))
                .setTypeId(rs.getInt("type_id"));
    }

    private void mapAnswersToQuestions(List<Question> questions, Map<Integer, Answer> answersMap) {
        questions.forEach(it ->
                it.getAnswers().addAll(
                        answersMap.values()
                                .stream()
                                .filter(ans -> it.getId() == ans.getQuestionId())
                                .collect(Collectors.toList())
                )
        );
    }

    private PreparedStatement getPSForQuestionByItemId(PreparedStatement preparedStatement, long id) throws SQLException {
        preparedStatement.setLong(1, id);

        return preparedStatement;
    }
}

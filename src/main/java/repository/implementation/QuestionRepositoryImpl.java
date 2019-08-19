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
import java.util.stream.IntStream;

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
        List<Answer> answers = new ArrayList<>();
        Map<Integer, Question> questionMap = new HashMap<>();
        Question.builder questionBuilder = new Question.builder();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuestionQueries.SELECT_ALL_QUESTIONS_BY_TEST_ITEM_ID.getQUERY());
             ResultSet rs = getPSForQuestionByItemId(preparedStatement, id).executeQuery()) {

            while (rs.next()) {
                int questionId = rs.getInt("question_id");

                buildAnswer(rs, answers, questionId);
                buildQuestionItem(rs, questionBuilder, questionMap, questionId);
            }


        } catch (SQLException e) {
            logger.error("error during getting questions and answers by id ", e);
        }


        mapAnswersToQuestions(questionMap, answers);

        return new ArrayList<>(questionMap.values());
    }


    private void buildQuestionItem(ResultSet rs, Question.builder questionBuilder,
                                   Map<Integer, Question> questionMap, int questionId) throws SQLException {

        if (questionMap.get(questionId) == null) {
            questionBuilder.setId(questionId)
                    .setText(rs.getString("question_text"))
                    .setUrl(rs.getString("question_image_url"))
                    .setTypeQuestion(new TypeQuestion(rs.getInt("type_id"), rs.getString("type_name")))
                    .setTypeId(rs.getInt("type_id"));

            questionMap.put(questionId, questionBuilder.build());
        }
    }

    private void buildAnswer(ResultSet rs, List<Answer> answers, int questionId) throws SQLException {
        int answerId = rs.getInt("answer_id");
        Answer answer = new Answer(
                answerId,
                questionId,
                rs.getString("answer"),
                rs.getBoolean("isCorrect"),
                rs.getString("error_descr")
        );

        answers.add(answer);
    }

    private void mapAnswersToQuestions(Map<Integer, Question> questionMap, List<Answer> answers) {
        logger.info(questionMap.values() + " questins");
        logger.info(answers + " answers");

        questionMap.values().forEach(
                question -> question.setAnswers(
                        answers.stream()
                                .filter(ans -> ans.getQuestionId() == question.getId())
                                .collect(Collectors.toList()))
        );

    }

    private PreparedStatement getPSForQuestionByItemId(PreparedStatement preparedStatement, long id) throws SQLException {
        preparedStatement.setLong(1, id);

        return preparedStatement;
    }
}

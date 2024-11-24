package entity.QuestionEntity;

import entity.Answer;
import entity.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Question entity to achieve 100% test coverage.
 */
class QuestionEntityTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String questionText = "Sample Question";
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Sample Answer 1", true));
        answers.add(new Answer("Sample Answer 2", false));
        answers.add(new Answer("Sample Answer 3", false));
        answers.add(new Answer("Sample Answer 4", false));

        // Act
        Question question = new Question(id, questionText, answers);

        // Assert
        assertEquals(id, question.getId());
        assertEquals(questionText, question.getQuestionText());
        assertEquals(answers, question.getAnswers());
    }

    @Test
    void testSetId() {
        // Arrange
        Question question = new Question(1, "Sample Question", new ArrayList<>());

        // Act
        question.setId(2);

        // Assert
        assertEquals(2, question.getId());
    }

    @Test
    void testSetQuestionText() {
        // Arrange
        Question question = new Question(1, "Old Question Text", new ArrayList<>());

        // Act
        question.setQuestionText("New Question Text");

        // Assert
        assertEquals("New Question Text", question.getQuestionText());
    }

    @Test
    void testAddAnswer() {
        // Arrange
        Question question = new Question(1, "Sample Question", new ArrayList<>());
        Answer answer = new Answer("Sample Answer", true);

        // Act
        question.addAnswer(answer);

        // Assert
        assertTrue(question.getAnswers().contains(answer));
    }

    @Test
    public void testToString() {
        // Arrange
        int id = 1;
        String questionText = "What is the capital of France?";
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Paris", true));
        answers.add(new Answer("London", false));
        answers.add(new Answer("Berlin", false));
        answers.add(new Answer("Madrid", false));

        Question question = new Question(id, questionText, answers);

        // Act
        String result = question.toString();

        // Expected format of toString() output
        String expected = "Question{id=1, questionText='What is the capital of France?', answers=[Answer{answerText='Paris', isCorrect=true}, Answer{answerText='London', isCorrect=false}, Answer{answerText='Berlin', isCorrect=false}, Answer{answerText='Madrid', isCorrect=false}]}";

        // Assert
        assertEquals(expected, result);
    }

}


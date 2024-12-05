package entity.AnswerEntity;

import entity.Answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Answer entity to achieve 100% test coverage.
 */
class AnswerEntityTest {

    @Test
    void testConstructor() {
        // Test constructor and getters
        Answer answer = new Answer("Sample Answer", true);
        assertEquals("Sample Answer", answer.getAnswerText());
        assertTrue(answer.isCorrect());
    }

    @Test
    void testGetAnswerText() {
        // Test getter for answerText
        Answer answer = new Answer("Sample Answer", true);
        assertEquals("Sample Answer", answer.getAnswerText());
    }

    @Test
    void testSetAnswerText() {
        // Test setter for answerText
        Answer answer = new Answer("Initial Answer", true);
        answer.setAnswerText("Updated Answer");
        assertEquals("Updated Answer", answer.getAnswerText());
    }

    @Test
    void testIsCorrect() {
        // Test getter for isCorrect
        Answer answer = new Answer("Sample Answer", true);
        assertTrue(answer.isCorrect());
    }

    @Test
    void testSetCorrect() {
        // Test setter for isCorrect
        Answer answer = new Answer("Sample Answer", false);
        answer.setCorrect(true);
        assertTrue(answer.isCorrect());
    }
}

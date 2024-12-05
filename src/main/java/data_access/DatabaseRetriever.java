package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import entity.Answer;
import entity.Question;

/**
 * Retrieves questions and ans`wers from the database.
 */
public class DatabaseRetriever {

    private static final String QUESTION_ID = "id";
    private static final String QUESTION_TEXT = "question_text";

    /**
     * Retrieves all questions from the database along with their answers.
     *
     * @return a list of Question objects with associated answers
     * @throws SQLException if a database access error occurs
     */
    public static LinkedList<Question> getAllQuestions() throws SQLException {
        final LinkedList<Question> questions = new LinkedList<>();
        final String query = "SELECT id, question_text FROM Questions";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                final int id = rs.getInt(QUESTION_ID);
                final String questionText = rs.getString(QUESTION_TEXT);
                final List<Answer> answers = getAllAnswersOnQ(id);

                // Create a Question object with its ID and answers
                final Question question = new Question(id, questionText, answers);
                questions.add(question);
            }
        }
        return questions;
    }

    /**
     * Retrieves all answers from the question object.
     *
     * @param questionID the ID of the question in which we are trying to find the answers.
     * @return a list of answer objects with associated question
     * @throws SQLException if a database access error occurs
     */
    public static List<Answer> getAllAnswersOnQ(int questionID) throws SQLException {
        final List<Answer> answers = new ArrayList<>();

        final String query = "SELECT answer_text, is_correct FROM Answers WHERE question_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, questionID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    final String answerText = rs.getString("answer_text");
                    final boolean isCorrect = rs.getBoolean("is_correct");

                    // Create an Answer object and add it to the list
                    final Answer answer = new Answer(answerText, isCorrect);
                    answers.add(answer);
                }
            }
        }
        catch (SQLException retrieverE) {
            retrieverE.printStackTrace();
            throw retrieverE;
        }

        return answers;
    }

    /**
     * Retrieves a subset of questions from the database within a specified range.
     *
     * @param startID the starting ID of the range.
     * @param endID   the ending ID of the range.
     * @return a list of Question objects with associated answers within the range.
     * @throws SQLException if a database access error occurs.
     */
    public static LinkedList<Question> getQuestionsInRange(int startID, int endID) throws SQLException {
        final LinkedList<Question> questions = new LinkedList<>();
        final String query = "SELECT id, question_text FROM Questions WHERE id BETWEEN ? AND ? LIMIT 24";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, startID);
            pstmt.setInt(2, endID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    final int id = rs.getInt(QUESTION_ID);
                    final String questionText = rs.getString(QUESTION_TEXT);

                    // Fetch associated answers for the current question
                    final List<Answer> answers = getAllAnswersOnQ(id);

                    // Create a Question object with its ID, text, and answers
                    final Question question = new Question(id, questionText, answers);
                    questions.add(question);
                }
            }
        }
        catch (SQLException exp) {
            exp.printStackTrace();
            throw exp;
        }

        return questions;
    }

    /**
     * Retrieves a subset of questions from the database within a specified range.
     *
     * @param startID the starting ID of the range.
     * @return a list of Question objects with associated answers within the range.
     * @throws SQLException if a database access error occurs.
     */
    public static LinkedList<Question> getQuestionsFromStart(int startID) throws SQLException {
        final LinkedList<Question> questions = new LinkedList<Question>();
        final String query = "SELECT id, question_text FROM Questions WHERE id >= ? ORDER BY id LIMIT 20";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, startID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    final int id = rs.getInt(QUESTION_ID);
                    final String questionText = rs.getString(QUESTION_TEXT);

                    // Fetch associated answers for the current question
                    final List<Answer> answers = getAllAnswersOnQ(id);

                    // Create a Question object with its ID, text, and answers
                    final Question question = new Question(id, questionText, answers);
                    questions.add(question);
                }
            }
        }
        catch (SQLException exp) {
            exp.printStackTrace();
            throw exp;
        }

        return questions;
    }

    /**
     * Retrieves a random selection of 40 questions from the database.
     * Each question includes its associated answers.
     *
     * @return a {@code LinkedList<Question>} containing 40 random questions with their respective answers.
     * @throws RuntimeException if a database access error occurs or the query fails.
     *
     */
    public static LinkedList<Question> getQuestionsRandom() {
        final LinkedList<Question> questions = new LinkedList<Question>();
        final String query = "SELECT id, question_text FROM Questions ORDER BY RANDOM() LIMIT 40";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    final int id = rs.getInt("id");
                    final String questionText = rs.getString(QUESTION_TEXT);

                    // Fetch associated answers for the current question
                    final List<Answer> answers = getAllAnswersOnQ(id);

                    // Create a Question object with its ID, text, and answers
                    final Question question = new Question(id, questionText, answers);
                    questions.add(question);
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
            throw new RuntimeException("Error retrieving random questions from database", exception);
        }

        return questions;
    }
}

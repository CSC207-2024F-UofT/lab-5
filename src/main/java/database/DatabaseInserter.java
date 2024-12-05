package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data_access.DatabaseConnection;

/**
 * Inserts questions and answers into the database.
 */
public class DatabaseInserter {

    /**
     * Inserts a new question into the Questions table.
     *
     * @param questionText the text of the question
     * @return the ID of the newly inserted question or -1 if the insertion failed
     * @throws SQLException if an error occurs during insertion
     */
    public static int insertQuestion(String questionText) throws SQLException {
        final String insertQuestionSql = "INSERT INTO Questions (question_text) VALUES (?)";
        int generatedId = -1;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuestionSql,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, questionText);
            stmt.executeUpdate();

            final var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        }
        return generatedId;
    }

    /**
     * Inserts an answer option into the Answers table.
     *
     * @param questionId the ID of the question this answer belongs to
     * @param answerText the text of the answer
     * @param isCorrect  whether this answer is the correct one
     * @throws SQLException if an error occurs during insertion
     */
    public static void insertAnswer(int questionId, String answerText, boolean isCorrect) throws SQLException {
        final String insertAnswerSql = "INSERT INTO Answers (question_id, answer_text, is_correct) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertAnswerSql)) {
            final int booleanNum = 3;
            stmt.setInt(1, questionId);
            stmt.setString(2, answerText);
            stmt.setBoolean(booleanNum, isCorrect);
            stmt.executeUpdate();
        }
    }
}

//    /**
//     * The main method serves as the entry point of the application.
//     * This method demonstrates inserting a sample question and its corresponding answers
//     * into the database. It uses {@link #insertQuestion(String)} to add a question
//     * and {@link #insertAnswer(int, String, boolean)} to add possible answers.
//     *
//     * @param args command-line arguments passed to the application (not used in this method)
//     */
//    public static void main(String[] args) {
//        try {
//            // Example data insertion
//            final int questionId = insertQuestion("What is the capital of France?");
//            insertAnswer(questionId, "Paris", true);
//            insertAnswer(questionId, "London", false);
//            insertAnswer(questionId, "Berlin", false);
//            insertAnswer(questionId, "Rome", false);
//
//            System.out.println("Question and answers with id " + questionId + " inserted successfully.");
//
//        }
//        catch (SQLException exception) {
//            exception.printStackTrace();
//        }
//     }


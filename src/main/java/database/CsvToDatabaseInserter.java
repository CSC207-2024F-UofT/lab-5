package database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import data_access.DatabaseConnection;

/**
 * A utility class for importing questions and their corresponding answers from a CSV file into a relational database.
 * Each question is inserted into the Questions table.
 * its associated answers are inserted into the Answers table.
 */
public class CsvToDatabaseInserter {

    /**
     * Reads a CSV file and inserts questions and answers into the database.
     * If a question already exists, its ID is reused for inserting associated answers.
     *
     * @param csvFilePath the path to the CSV file containing the questions and answers
     * @throws IOException if there is an error reading the file
     * @throws CsvException if the CSV file is improperly formatted
     * @throws SQLException if there is an error with the database operation
     */
    public static void insertFromCsv(String csvFilePath) {
        final String insertQuestionSql = "INSERT INTO Questions (question_text) VALUES (?)";
        final String insertAnswerSql = "INSERT INTO Answers (question_id, answer_text, is_correct) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {

            final List<String[]> records = csvReader.readAll();

            for (String[] recordCsv : records) {
                final String questionText = recordCsv[0];
                final String answerText = recordCsv[1];
                final boolean isCorrect = Boolean.parseBoolean(recordCsv[2]);

                // Calls a helper method to insert the question (if it doesn't exist) and retrieves its ID.
                final int questionId = getOrInsertQuestion(conn, insertQuestionSql, questionText);

                // Prepares and executes an SQL statement to insert the answer associated with the question ID.
                try (PreparedStatement answerStmt = conn.prepareStatement(insertAnswerSql)) {
                    final int boolNum = 3;
                    answerStmt.setInt(1, questionId);
                    answerStmt.setString(2, answerText);
                    answerStmt.setBoolean(boolNum, isCorrect);
                    answerStmt.executeUpdate();
                }
            }

            System.out.println("Data inserted from CSV successfully.");
        }
        catch (IOException | CsvException | SQLException csvE) {
            csvE.printStackTrace();
        }
    }

    /**
     * Retrieves the ID of an existing question or inserts a new one if it does not exist.
     *
     * @param conn the database connection
     * @param insertQuestionSql the SQL query to insert a new question
     * @param questionText the text of the question to retrieve or insert
     * @return the ID of the question
     * @throws SQLException if there is an error with the database operation
     */
    private static int getOrInsertQuestion(Connection conn, String insertQuestionSql,
                                           String questionText) throws SQLException {
        int questionId = -1;

        // Check if the question already exists
        final String findQuestionSql = "SELECT id FROM Questions WHERE question_text = ?";
        try (PreparedStatement findStmt = conn.prepareStatement(findQuestionSql)) {
            findStmt.setString(1, questionText);
            // store the retrieved rows with the question_text
            final var rs = findStmt.executeQuery();
            if (rs.next()) {
                questionId = rs.getInt("id");
            }
        }

        // If the question does not exist, insert it
        if (questionId == -1) {
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuestionSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertStmt.setString(1, questionText);
                insertStmt.executeUpdate();
                final var rs = insertStmt.getGeneratedKeys();
                if (rs.next()) {
                    questionId = rs.getInt(1);
                }
            }
        }

        if (questionId == -1) {
            throw new SQLException("Failed to insert or find question: " + questionText);
        }

        return questionId;
    }

    /**
     * Main method to execute the CsvToDatabaseInserter.
     * This demonstrates how to import questions and answers from a CSV file into the database.
     *
     * <p>It reads a predefined CSV file located at {@code src/main/java/database/questions.csv},
     * parses the file, and inserts the data into the database.</p>
     * @param args command-line arguments (not used in this method)
     */
    public static void main(String[] args) {
        final String csvFilePath = "src/main/java/database/questions.csv";
        insertFromCsv(csvFilePath);
    }
}

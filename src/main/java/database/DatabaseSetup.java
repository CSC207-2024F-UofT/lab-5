package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import data_access.DatabaseConnection;

/**
 * Initializes the database by creating the Questions and Answers tables.
 */
public class DatabaseSetup {

    /**
     * Initializes the database by creating necessary tables.
     */
    public static void initializeDatabase() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create the Questions table
            final String createQuestionsTable = "CREATE TABLE IF NOT EXISTS Questions ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "question_text TEXT NOT NULL)";
            stmt.execute(createQuestionsTable);

            // Create the Answers table
            final String createAnswersTable = "CREATE TABLE IF NOT EXISTS Answers ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "question_id INT, "
                    + "answer_text VARCHAR(255), "
                    + "is_correct BOOLEAN, "
                    + "FOREIGN KEY (question_id) REFERENCES Questions(id))";
            stmt.execute(createAnswersTable);

            System.out.println("Database and tables created successfully.");

        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Entry point of the application.
     * Initializes the database by creating the required tables.
     * This method should be run at the start to set up the database.
     *
     * @param args command-line arguments passed to the application (not used in this method).
     */
    public static void main(String[] args) {
        initializeDatabase();
    }
}

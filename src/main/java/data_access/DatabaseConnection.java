package data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the connection to the H2 database.
 */
public class DatabaseConnection {

    // Embedded H2 database URL
    private static final String DB_URL = "jdbc:h2:file:./src/main/java/database/database1";
    private static final String DB_USER = "huang";
    private static final String DB_PASSWORD = "";

    /**
     * Establishes and returns a connection to the H2 database.
     *
     * @return a Connection object connected to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}

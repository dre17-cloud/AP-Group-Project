package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles MySQL database connection.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3307/smartship_db";
    private static final String USER = "root";
    private static final String PASSWORD = "usbw"; 
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println(" Database connected successfully!");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            }
        }
        return connection;
    }
}

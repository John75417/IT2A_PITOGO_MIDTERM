package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:sqlite:event_tickets.db";

    public static Connection getConnection()
            throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase()
            throws SQLException {

        String sql = """
            CREATE TABLE IF NOT EXISTS tickets (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                attendee_name TEXT NOT NULL,
                ticket_type TEXT NOT NULL,
                ticket_price REAL NOT NULL,
                payment_status TEXT NOT NULL
            )
            """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
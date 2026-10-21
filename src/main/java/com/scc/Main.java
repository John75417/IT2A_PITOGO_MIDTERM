import dao.DatabaseConnection;
import ui.Menu;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            DatabaseConnection.initializeDatabase();

            System.out.println(
                    "SQLite database connected successfully!"
            );

            Menu menu = new Menu();
            menu.start();

        } catch (SQLException e) {
            System.err.println(
                    "Application error: " + e.getMessage()
            );
        }
    }
}
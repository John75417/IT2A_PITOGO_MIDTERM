package dao;

import model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public boolean addTicket(Ticket ticket)
            throws SQLException {

        String sql = """
            INSERT INTO tickets
                (attendee_name, ticket_type,
                 ticket_price, payment_status)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, ticket.getAttendeeName());
            statement.setString(2, ticket.getTicketType());
            statement.setDouble(3, ticket.getTicketPrice());
            statement.setString(4, ticket.getPaymentStatus());

            return statement.executeUpdate() == 1;
        }
    }

    public List<Ticket> getAllTickets()
            throws SQLException {

        List<Ticket> tickets = new ArrayList<>();

        String sql = """
            SELECT id, attendee_name, ticket_type,
                   ticket_price, payment_status
            FROM tickets
            ORDER BY id
            """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ticket ticket = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getString("attendee_name"),
                        resultSet.getString("ticket_type"),
                        resultSet.getDouble("ticket_price"),
                        resultSet.getString("payment_status")
                );

                tickets.add(ticket);
            }
        }

        return tickets;
    }
}
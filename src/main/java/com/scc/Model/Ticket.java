package model;

public class Ticket {
    private final int id;
    private final String attendeeName;
    private final String ticketType;
    private final double ticketPrice;
    private final String paymentStatus;

    public Ticket(int id, String attendeeName, String ticketType,
                  double ticketPrice, String paymentStatus) {
        this.id = id;
        this.attendeeName = attendeeName;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.paymentStatus = paymentStatus;
    }

    public Ticket(String attendeeName, String ticketType,
                  double ticketPrice, String paymentStatus) {
        this(0, attendeeName, ticketType, ticketPrice, paymentStatus);
    }

    public int getId() {
        return id;
    }

    public String getAttendeeName() {
        return attendeeName;
    }

    public String getTicketType() {
        return ticketType;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}
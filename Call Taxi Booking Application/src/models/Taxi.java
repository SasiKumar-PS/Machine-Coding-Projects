package models;

import java.util.List;

public class Taxi {
    private int id;
    private Point currentPoint;
    private int availableTime;
    private int amount;
    private List<Booking> bookings;

    public Taxi(int id, Point currentPoint, int availableTime, int amount, List<Booking> bookings) {
        this.id = id;
        this.currentPoint = currentPoint;
        this.availableTime = availableTime;
        this.amount = amount;
        this.bookings = bookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

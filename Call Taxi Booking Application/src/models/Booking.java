package models;

public class Booking {
    public static int lastBookingId = 0;
    private int id;
    private Taxi taxi;
    private Customer customer;
    private Point pickupPoint;
    private Point dropPoint;
    private int pickupTime;
    private int dropTime;
    private int amount;

    public Booking(int id, Taxi taxi, Customer customer, Point pickupPoint, Point dropPoint, int pickupTime, int dropTime, int amount) {
        this.id = id;
        this.taxi = taxi;
        this.customer = customer;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Point getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(Point pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public Point getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(Point dropPoint) {
        this.dropPoint = dropPoint;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getDropTime() {
        return dropTime;
    }

    public void setDropTime(int dropTime) {
        this.dropTime = dropTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

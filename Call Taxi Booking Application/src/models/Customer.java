package models;

public class Customer {
    private int id;
    private Point pickupPoint;
    private Point dropPoint;
    private int requestedTime;

    public Customer(int id, Point pickupPoint, Point dropPoint, int requestedTime) {
        this.id = id;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.requestedTime = requestedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
    }
}

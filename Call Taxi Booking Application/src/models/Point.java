package models;

import java.util.List;

public class Point {
    private char id;
    private List<Taxi> availableTaxis;

    public Point(char id, List<Taxi> availableTaxis) {
        this.id = id;
        this.availableTaxis = availableTaxis;
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public List<Taxi> getAvailableTaxis() {
        return availableTaxis;
    }

    public void setAvailableTaxis(List<Taxi> availableTaxis) {
        this.availableTaxis = availableTaxis;
    }
}

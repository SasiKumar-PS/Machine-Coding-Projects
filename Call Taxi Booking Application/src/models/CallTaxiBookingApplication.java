package models;

import java.util.List;
import java.util.Map;

public class CallTaxiBookingApplication {
    private List<Taxi> taxis;
    private List<Point> points;

    public CallTaxiBookingApplication(List<Taxi> taxis, List<Point> points) {
        this.taxis = taxis;
        this.points = points;
    }

    public List<Taxi> getTaxis() {
        return taxis;
    }

    public void setTaxis(List<Taxi> taxis) {
        this.taxis = taxis;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}

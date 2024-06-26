package controllers;

import exceptions.NoTaxiAvailableException;
import models.*;
import services.BookingService;
import services.BookingServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private CallTaxiBookingApplication callTaxiBookingApplication;
    private BookingService bookingService = new BookingServiceImpl();

    public BookingController() {}
    public BookingController(CallTaxiBookingApplication callTaxiBookingApplication) {
        this.callTaxiBookingApplication = callTaxiBookingApplication;
    }

    public void startCallTaxiBookingApplication(int taxiCount, int pointCount) {
        Point startingPoint = new Point('A', new ArrayList<>());

        List<Taxi> taxis = new ArrayList<>();
        for(int i=1; i<=taxiCount; i++) {
            Taxi taxi = new Taxi(i, startingPoint, 0, 0, new ArrayList<>());
            taxis.add(taxi);
            startingPoint.getAvailableTaxis().add(taxi);
        }

        List<Point> points = new ArrayList<>();
        points.add(startingPoint);
        char nextPointName = 'B';
        for(int i=1; i<=pointCount-1; i++) {
            points.add(new Point(nextPointName++, new ArrayList<>()));
        }

        this.callTaxiBookingApplication = new CallTaxiBookingApplication(taxis, points);
        System.out.println("Call taxi booking application initialized!");
    }

    public void newBooking(int customerId, char pickup, char drop, int pickupTime) {
        Point pickupPoint = callTaxiBookingApplication.getPoints().get(pickup-'A');
        Point dropPoint = callTaxiBookingApplication.getPoints().get(drop-'A');
        Customer customer = new Customer(customerId, pickupPoint, dropPoint, pickupTime);
        try {
            bookingService.newBooking(callTaxiBookingApplication, customer, pickupPoint, dropPoint, pickupTime);
        } catch (NoTaxiAvailableException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showEarnings() {
        bookingService.showEarnings(callTaxiBookingApplication);
    }
}

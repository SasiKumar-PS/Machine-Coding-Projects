package services;

import exceptions.NoTaxiAvailableException;
import models.CallTaxiBookingApplication;
import models.Customer;
import models.Point;

public interface BookingService {
    void newBooking(CallTaxiBookingApplication callTaxiBookingApplication, Customer customer, Point pickupPoint, Point dropPoint, int time) throws NoTaxiAvailableException;
    void showEarnings(CallTaxiBookingApplication callTaxiBookingApplication);
}

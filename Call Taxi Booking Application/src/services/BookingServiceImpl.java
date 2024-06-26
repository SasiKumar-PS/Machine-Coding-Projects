package services;

import exceptions.NoTaxiAvailableException;
import models.*;

public class BookingServiceImpl implements BookingService{
    @Override
    public void newBooking(CallTaxiBookingApplication callTaxiBookingApplication, Customer customer, Point pickupPoint, Point dropPoint, int pickupTime) throws NoTaxiAvailableException {

        Taxi bestTaxi = null;
        int bestPickupTime = pickupTime;
        int pickupPointIdx = pickupPoint.getId() - 'A';

        for (Taxi taxi : pickupPoint.getAvailableTaxis()) {
            if (taxi.getAvailableTime() <= pickupTime) {
                if (bestTaxi == null) {
                    bestTaxi = taxi;
                } else if (bestTaxi.getAmount() > taxi.getAmount()) {
                    bestTaxi = taxi;
                }
            }
        }

        if(bestTaxi == null) {
            int left = pickupPointIdx - 1;
            int right = pickupPointIdx + 1;

            Taxi bestLeftTaxi = null;
            Taxi bestRightTaxi = null;
            // checking both side simultaneously
            while (left >= 0 && right < callTaxiBookingApplication.getPoints().size()) {
                for (Taxi taxi : callTaxiBookingApplication.getPoints().get(left).getAvailableTaxis()) {
                    if (taxi.getAvailableTime() <= pickupTime) {
                        if (bestLeftTaxi == null) {
                            bestLeftTaxi = taxi;
                        } else if (bestLeftTaxi.getAmount() > taxi.getAmount()) {
                            bestLeftTaxi = taxi;
                        }
                    }
                }


                for (Taxi taxi : callTaxiBookingApplication.getPoints().get(right).getAvailableTaxis()) {
                    if (taxi.getAvailableTime() <= pickupTime) {
                        if (bestRightTaxi == null) {
                            bestRightTaxi = taxi;
                        } else if (bestRightTaxi.getAmount() > taxi.getAmount()) {
                            bestRightTaxi = taxi;
                        }
                    }
                }

                if (bestLeftTaxi != null && bestRightTaxi != null) {
                    if (bestLeftTaxi.getAmount() < bestRightTaxi.getAmount()) {
                        bestTaxi = bestLeftTaxi;
                        bestPickupTime += Math.abs(pickupPointIdx - left);
                    } else {
                        bestTaxi = bestRightTaxi;
                        bestPickupTime += Math.abs(pickupPointIdx - right);
                    }
                    break;
                }
                else if (bestLeftTaxi != null) {
                    bestTaxi = bestLeftTaxi;
                    bestPickupTime += Math.abs(pickupPointIdx - left);
                    break;
                }
                else if (bestRightTaxi != null) {
                    bestTaxi = bestRightTaxi;
                    bestPickupTime += Math.abs(pickupPointIdx - right);
                    break;
                }

                left--;
                right++;
            }

            if(bestTaxi == null) {
                // checking left side if right side has no taxi
                while (left >= 0) {
                    for (Taxi taxi : callTaxiBookingApplication.getPoints().get(left).getAvailableTaxis()) {
                        if (taxi.getAvailableTime() <= pickupTime) {
                            if (bestLeftTaxi == null) {
                                bestLeftTaxi = taxi;
                            } else if (bestLeftTaxi.getAmount() > taxi.getAmount()) {
                                bestLeftTaxi = taxi;
                            }
                        }
                    }

                    if (bestLeftTaxi != null) {
                        bestTaxi = bestLeftTaxi;
                        bestPickupTime += Math.abs(pickupPointIdx - left);
                        break;
                    }
                    left--;
                }
            }

            if(bestTaxi == null) {
                // checking right side if left side has no taxi
                while (right < callTaxiBookingApplication.getPoints().size()) {
                    for (Taxi taxi : callTaxiBookingApplication.getPoints().get(right).getAvailableTaxis()) {
                        if (taxi.getAvailableTime() <= pickupTime) {
                            if (bestRightTaxi == null) {
                                bestRightTaxi = taxi;
                            } else if (bestRightTaxi.getAmount() > taxi.getAmount()) {
                                bestRightTaxi = taxi;
                            }
                        }
                    }

                    if (bestRightTaxi != null) {
                        bestTaxi = bestRightTaxi;
                        bestPickupTime += Math.abs(pickupPointIdx - left);
                        break;
                    }
                    right--;
                }
            }
        }

        if(bestTaxi == null) {
            throw new NoTaxiAvailableException("There is no taxi available at the moment, Booking Cancelled!");
        }

        int distance = Math.abs((pickupPoint.getId()-'A') - (dropPoint.getId()-'A'));
        int dropTime = bestPickupTime + distance;
        int amount = (((distance*15)-5) * 10) + 100;
        Booking booking = new Booking(++Booking.lastBookingId, bestTaxi, customer, pickupPoint, dropPoint, bestPickupTime, dropTime, amount);

        bestTaxi.getBookings().add(booking);
        bestTaxi.setAvailableTime(dropTime);
        bestTaxi.setAmount(bestTaxi.getAmount()+amount);
        pickupPoint.getAvailableTaxis().remove(bestTaxi);
        dropPoint.getAvailableTaxis().add(bestTaxi);

        System.out.println("Taxi can be allotted.");
        System.out.println(bestTaxi.getId() + " is allotted");
    }

    @Override
    public void showEarnings(CallTaxiBookingApplication callTaxiBookingApplication) {
        for(Taxi taxi : callTaxiBookingApplication.getTaxis()) {
            System.out.println("Total earned by Taxi: " + taxi.getId() + " is " + taxi.getAmount());

            for(Booking booking : taxi.getBookings()) {
                System.out.println(booking.getId() + " "
                        + booking.getCustomer().getId() + " "
                        + booking.getPickupPoint().getId() + " "
                        + booking.getDropPoint().getId() + " "
                        + booking.getPickupTime() + " "
                        + booking.getDropTime() + " "
                        + booking.getAmount()
                );
            }
            System.out.println();
        }
    }
}

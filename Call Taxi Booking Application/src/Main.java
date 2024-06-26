import controllers.BookingController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingController bookingController = new BookingController();
        Scanner scanner = new Scanner(System.in);

        label:
        while(true) {
            String line = scanner.nextLine();
            switch (line) {
                case "Call taxi booking":
                    bookingController.startCallTaxiBookingApplication(scanner.nextInt(), scanner.nextInt());
                    line = scanner.nextLine();
                    break;
                case "Display earnings":
                    bookingController.showEarnings();
                    break;
                case "new Booking":
                    String[] bookingInput;
                    bookingInput = scanner.nextLine().split(" ");
                    int customerId = Integer.parseInt(bookingInput[bookingInput.length - 1]);

                    line = scanner.nextLine();
                    char pickup = line.charAt(line.length() - 1);

                    line = scanner.nextLine();
                    char drop = line.charAt(line.length() - 1);

                    bookingInput = scanner.nextLine().split(" ");
                    int pickupTime = Integer.parseInt(bookingInput[bookingInput.length - 1]);

                    bookingController.newBooking(customerId, pickup, drop, pickupTime);
                    break;
                default:
                    break label;
            }
        }
    }
}
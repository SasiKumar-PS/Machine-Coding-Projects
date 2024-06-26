package Controller;

import Enums.CommandType;
import Enums.DisplayType;
import Enums.VehicleType;
import Service.ParkingService;

public class ParkingController {
    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    public String processInput(String[] input) {
        CommandType type = CommandType.valueOf(input[0]);
        switch (type) {
            case create_parking_lot: initiateParking(input[1], Integer.parseInt(input[2]), Integer.parseInt(input[3]));

            case park_vehicle: bookParking(input[2], input[3], VehicleType.valueOf(input[1]));
                                
            case unpark_vehicle: {
                String[] info = input[1].split("_");

                int floorNumber = Integer.parseInt(info[1]);
                int slotNumber = Integer.parseInt(info[2]);
                unPark(floorNumber, slotNumber);
            }
                                
            case display: display(DisplayType.valueOf(input[1]), VehicleType.valueOf(input[2]));
                            
            case exit: return "exit";
        }
        return "Sucess";
    }


    public void initiateParking(String ticket, int numberOfFloors, int numberOfSlots) {
        parkingService.initiateParking(ticket, numberOfFloors, numberOfSlots);
    }

    public void bookParking(String registrationNumber, String color, VehicleType vehicleType) {
        try {
            parkingService.bookParking(registrationNumber, color, vehicleType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void unPark(int floorNumber, int slotNumber) {
        try {
            parkingService.unPark(floorNumber, slotNumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void display(DisplayType displayType, VehicleType vehicleType) {        
        switch (displayType) {
            case free_slots: parkingService.displayFreeSlots(vehicleType);
            case free_count : parkingService.displayCountOfFreeSlots(vehicleType);
            case occupied_slots : parkingService.displayOccupiedSlots(vehicleType);
        }
    }
}

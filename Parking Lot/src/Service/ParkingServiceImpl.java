package Service;

import Enums.*;
import Exceptions.*;
import Model.*;

public class ParkingServiceImpl implements  ParkingService{
    private ParkingLot parkingLot;

    @Override
    public void initiateParking(String ticket, int numberOfFloors, int numberOfSlots){
        parkingLot = ParkingLot.builder()
                                .setTicket(ticket)
                                .setNumberOfFloors(numberOfFloors)
                                .setNumberOfSlots(numberOfSlots)
                                .build();

        System.out.println("Parking Lot initiated with " + numberOfFloors + " floors and " + numberOfSlots + " slots per floor");
    }

    @Override
    public void bookParking(String registrationNumber, String color, VehicleType vehicleType) throws SlotFullException {

        Slot freeSlot = null;
        for(Floor floor : parkingLot.getFloors()) {
            for(Slot slot : floor.getSlots()) {
                if( slot.getVehicleType().equals(vehicleType) &&
                        slot.getSlotState().equals(SlotState.FREE) ) {
                    slot.setSlotState(SlotState.OCCUPIED);
                    freeSlot = slot;
                    break;
                }
            }
            if(freeSlot != null) break;
        }

        if(freeSlot == null){
            throw new SlotFullException("Parking Lot is Full!");
        }

        System.out.println("Parked vehicle. Ticket ID: " + parkingLot.getTicket() + "_"+ freeSlot.getFloorNumber() + "_" +  freeSlot.getNumber());
    }

    @Override
    public void unPark(int floorNumber, int slotNumber) throws Exception {

        if(floorNumber <= 0 || floorNumber > parkingLot.getFloors().length) {
            throw new InvalidFloorNumberException("Invalid Floor Number");
        }
        if(slotNumber <= 0 || slotNumber > parkingLot.getFloors()[floorNumber].getSlots().length) {
            throw new InvalidSlotNumberException("Invalid Slot Number");
        }

        Slot slot = parkingLot.getFloors()[floorNumber-1].getSlots()[slotNumber-1];
        Vehicle parkedVehicle = slot.getParkedVehicle();
        System.out.println("Unparked vehicle with Registration Number: " + parkedVehicle.getRegistrationNumber() + " and Color: " + parkedVehicle.getColor());

        slot.setParkedVehicle(null);
        slot.setSlotState(SlotState.FREE);
    }

    @Override
    public void displayFreeSlots(VehicleType vehicleType) {
        for(Floor floor : parkingLot.getFloors()) {
            StringBuilder freeSlots = new StringBuilder("Free slots for "+ " " + vehicleType + " " + " on Floor " + floor.getNumber() + ": ");
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType) && slot.getSlotState().equals(SlotState.FREE)) {
                    freeSlots.append(slot.getNumber()).append(",");
                }
            }

            System.out.println(freeSlots.toString());
        }
    }

    @Override
    public void displayCountOfFreeSlots(VehicleType vehicleType) {

        for(Floor floor : parkingLot.getFloors()) {
            String freeSlots = "No. of free slots for " + vehicleType + " on Floor " + floor.getNumber() + ": ";
            int count = 0;
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType) && slot.getSlotState().equals(SlotState.FREE)) {
                    count++;
                }
            }
            freeSlots += count;
            System.out.println(freeSlots);
        }
    }

    @Override
    public void displayOccupiedSlots(VehicleType vehicleType) {
        for(Floor floor : parkingLot.getFloors()) {
            StringBuilder freeSlots = new StringBuilder("Free slots for " + vehicleType + " on Floor " + floor.getNumber() + ": ");
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType) && slot.getSlotState().equals(SlotState.OCCUPIED)) {
                    freeSlots.append(slot.getNumber()).append(",");
                }
            }

            System.out.println(freeSlots.toString());
        }
    }

    @Override
    public void displayCountOfOccupiedSlots(VehicleType vehicleType) {
        for(Floor floor : parkingLot.getFloors()) {
            String freeSlots = "No. of free slots for " + vehicleType + "on Floor " + floor.getNumber() + ": ";
            int count = 0;
            for(Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType) && slot.getSlotState().equals(SlotState.OCCUPIED)) {
                    count++;
                }
            }
            freeSlots += count;
            System.out.println(freeSlots);
        }
    }
}

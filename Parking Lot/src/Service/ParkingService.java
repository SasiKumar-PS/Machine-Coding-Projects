package Service;

import Enums.VehicleType;
import Exceptions.SlotFullException;

public interface ParkingService {
    void initiateParking(String ticket, int floorCount, int slotCount);
    public void bookParking(String registrationNumber, String color, VehicleType vehicleType) throws SlotFullException;
    public void unPark(int floorNumber, int slotNumber) throws Exception;
    public void displayFreeSlots(VehicleType vehicleType);
    public void displayCountOfFreeSlots(VehicleType vehicleType);
    public void displayOccupiedSlots(VehicleType vehicleType);
    public void displayCountOfOccupiedSlots(VehicleType vehicleType);
}

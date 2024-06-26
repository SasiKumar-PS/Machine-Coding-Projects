package Model;

import Enums.SlotState;
import Enums.VehicleType;

public class Slot {
    private int number;
    private int floorNumber;
    private VehicleType vehicleType;
    private Vehicle parkedVehicle;
    private SlotState slotState = SlotState.FREE;

    public Slot(int number, int floorNumber) {
        this.number = number;
        this.floorNumber = floorNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public SlotState getSlotState() {
        return slotState;
    }

    public void setSlotState(SlotState slotState) {
        this.slotState = slotState;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }
}

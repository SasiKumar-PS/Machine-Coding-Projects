package Model;

import Enums.VehicleType;

public class Floor {
    private int number;
    private int numberOfSlots;
    private Slot[] slots;

    public Floor(int number, int numberOfSlots) {
        this.number = number;
        this.numberOfSlots = numberOfSlots;
        slots = new Slot[numberOfSlots];

        for(int i=0; i<numberOfSlots; i++) {
            slots[i] = new Slot(i+1, number);

            if(i == 0) slots[i].setVehicleType(VehicleType.TRUCK);
            else if(i < 3) slots[i].setVehicleType(VehicleType.BIKE);
            else slots[i].setVehicleType(VehicleType.CAR);
        }

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int floorNumber) {
        this.number = floorNumber;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public void setSlots(Slot[] slots) {
        this.slots = slots;
    }
}

package Model;


import Exceptions.InvalidFloorNumberException;
import Exceptions.InvalidSlotNumberException;

// need to implement Builder pattern to prevent invalid input sizes
public class ParkingLot {
    private String ticket;
    private int numberOfFloors;
    private  int numberOfSlots;
    private Floor[] floors;

    private ParkingLot(String ticket, int numberOfFloors, int numberOfSlots) {
        this.ticket = ticket;
        this.numberOfFloors = numberOfFloors;
        this.numberOfSlots = numberOfSlots;

        floors = new Floor[numberOfFloors];
        for(int i=0; i<numberOfFloors; i++){
            floors[i] = new Floor(i+1, numberOfSlots);
        }
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private String ticket;
        private int numberOfFloors;
        private  int numberOfSlots;

        public Builder setTicket(String ticket) {
            this.ticket = ticket;
            return this;
        }

        public Builder setNumberOfFloors(int numberOfFloors) {
            this.numberOfFloors = numberOfFloors;
            return this;
        }

        public Builder setNumberOfSlots(int numberOfSlots) {
            this.numberOfSlots = numberOfSlots;
            return this;
        }

        public ParkingLot build() {
            if(numberOfFloors < 1) throw new RuntimeException("Invalid Floor number");
            if(numberOfSlots < 1) throw new RuntimeException("Invalid Slot number");

            return new ParkingLot(ticket, numberOfFloors, numberOfSlots);
        }
    }
}

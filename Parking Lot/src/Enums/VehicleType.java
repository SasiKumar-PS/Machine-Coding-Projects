package Enums;

public enum VehicleType {
    TRUCK("TRUCK"),
    CAR("CAR"),
    BIKE("BIKE");

    private String s;

    VehicleType(String s) {
        this.s = s;
    }
}

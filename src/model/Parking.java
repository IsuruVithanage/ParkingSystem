package model;

public class Parking {
    private String vehicleNumber;
    private String vehicleType;
    private String slot;
    private String time;

    public Parking() {
    }

    public Parking(String vehicleNumber, String vehicleType, String slot, String time) {
        setVehicleNumber(vehicleNumber);
        setVehicleType(vehicleType);
        setSlot(slot);
        setTime(time);
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package model;

public class Vehicle {
    String vehicleNumber;
    String vehicleType;
    String maxweight;
    String noOfPassenger;

    public Vehicle() {
    }

    public Vehicle(String vehicleNumber, String vehicleType, String maxweight, String noOfPassenger) {
        setVehicleNumber(vehicleNumber);
        setVehicleType(vehicleType);
        setMaxweight(maxweight);
        setNoOfPassenger(noOfPassenger);
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

    public String getMaxweight() {
        return maxweight;
    }

    public void setMaxweight(String maxweight) {
        this.maxweight = maxweight;
    }

    public String getNoOfPassenger() {
        return noOfPassenger;
    }

    public void setNoOfPassenger(String noOfPassenger) {
        this.noOfPassenger = noOfPassenger;
    }
}

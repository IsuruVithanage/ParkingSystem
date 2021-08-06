package view.tm;

import javafx.scene.control.Button;

public class VehicleTM {
    private String vehicleNumber;
    private String vehicleType;
    private String maxweight;
    private String noOfPassenger;
    private Button btn;

    public VehicleTM(String name, String nic, String lisenceNo, String address, String contact, Button btn) {
    }

    public VehicleTM(String vehicleNumber, String vehicleType, String maxweight, String noOfPassenger, Button btn) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.maxweight = maxweight;
        this.noOfPassenger = noOfPassenger;
        this.btn=btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}

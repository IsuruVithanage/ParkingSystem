package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import model.Driver;
import model.Vehicle;

import java.util.ArrayList;

public class AddDriversController {

    public JFXButton addDriver;
    public JFXTextField name;
    public JFXTextField nic;
    public JFXTextField licenseNo;
    public JFXTextField contact;
    public JFXTextArea address;

    static ArrayList<Driver> driverList = new ArrayList<>();
    public Label lbError;

    public void initialize(){
        name.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!name.getText().matches("[a-zA-z&&[^0-9]]+\\s[a-zA-z&&[^0-9]]+")){
                    name.setText("");
                }
            }
            for (Driver temp:driverList
            ) {
                if (name.getText().equals(temp.getName())){
                    name.setText("");
                }
            }

        });

        nic.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!nic.getText().matches("\\d{11}[V]")){
                    nic.setText("");
                }
            }

            for (Driver temp:driverList
            ) {
                if (nic.getText().equals(temp.getNic())){
                    nic.setText("");
                }
            }


        });

        licenseNo.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!licenseNo.getText().matches("[B]\\d{7}")){
                    licenseNo.setText("");
                }
            }

            for (Driver temp:driverList
            ) {
                if (licenseNo.getText().equals(temp.getLisenceNo())){
                    licenseNo.setText("");
                }
            }


        });

        contact.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!contact.getText().matches("\\d{10}")){
                    contact.setText("");
                }
            }

            for (Driver temp:driverList
            ) {
                if (contact.getText().equals(temp.getContact())){
                    contact.setText("");
                }
            }


        });

        address.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!address.getText().matches("\\w+")){
                    address.setText("");
                }
            }

        });

        name.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                nic.requestFocus();
            }
        });

        nic.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                licenseNo.requestFocus();
            }
        });

        licenseNo.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                contact.requestFocus();
            }
        });

        contact.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                address.requestFocus();
            }
        });

    }

    public void addDriver(ActionEvent actionEvent) {
        if (name.getText() == null || name.getText().trim().isEmpty()) {
            lbError.setText("Driver Name is empty");
        }else if (nic.getText() == null || nic.getText().trim().isEmpty()){
            lbError.setText("\t\tNIC is empty");
        }else if (licenseNo.getText() == null || licenseNo.getText().trim().isEmpty()){
            lbError.setText("License Number is empty");
        }else if (contact.getText() == null || contact.getText().trim().isEmpty()){
            lbError.setText("Contact Number is empty");
        }else if (address.getText() == null || address.getText().trim().isEmpty()){
            lbError.setText("\tAddress is empty");
        }else {
            Driver driver=new Driver(name.getText(),nic.getText(),licenseNo.getText(),contact.getText(),address.getText());
            driverList.add(driver);
        }
    }

    public void clear(ActionEvent actionEvent) {
        name.clear();
        nic.clear();
        licenseNo.clear();
        contact.clear();
        address.clear();
    }
}

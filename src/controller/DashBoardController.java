package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import model.Driver;
import model.Parking;
import model.Vehicle;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static controller.AddDriversController.driverList;
import static controller.AddVehicleController.vehicleList;

public class DashBoardController {
    public Label label;

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    public AnchorPane contexDash;
    public JFXComboBox cmbVehicle;
    public Label lbtype;
    public Label lbdate;
    public JFXTextField time;
    public JFXComboBox<String> cmbDrivers;

    static ArrayList<Delivery> deliveryList=new ArrayList<>();
    static ArrayList<Parking> parkingList=new ArrayList<>();
    public Label slotlb;
    public JFXButton parking;
    public JFXButton delivery;
    static String[][] parkingSlot={{"Van",null},{"Van",null},{"Van",null},{"Van",null},{"CargoLorry",null},{"CargoLorry",null},{"CargoLorry",null},{"CargoLorry",null},{"CargoLorry",null},{"CargoLorry",null},{"CargoLorry",null},{"Van",null},{"Van",null},{"Bus",null}};
    public Label lbError;


    public void initialize(){
        //Vehicle ComboBox
        ObservableList<String> tmObservableList = FXCollections.observableArrayList();
        for (Vehicle temp:vehicleList
        ) {
            tmObservableList.add(temp.getVehicleNumber());

        }
        cmbVehicle.setItems(tmObservableList);

        cmbVehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String type="none";
            for (Vehicle temp1:vehicleList
                 ) {
                if (newValue.equals(temp1.getVehicleNumber())){
                    type=temp1.getVehicleType();
                }

            }
            lbtype.setText(type);
            lbError.setText("");
            slot();
        });

        //Driver ComboBox
        ObservableList<String> tmDriverObservableList = FXCollections.observableArrayList();
        for (Driver temp:driverList
        ) {
            tmDriverObservableList.add(temp.getName());

        }
        cmbDrivers.setItems(tmDriverObservableList);

        cmbDrivers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String stateDelivery="No";
            String stateParking="No";
            if (!deliveryList.isEmpty()) {
                for (Parking temp : parkingList
                ) {
                    if (temp.getVehicleNumber().equals(cmbVehicle.getSelectionModel().getSelectedItem().toString())) {
                        stateParking= "Yes";
                        break;
                    }
                }
                for (Delivery temp : deliveryList
                ) {
                    if (temp.getDriveerName().equals(newValue)) {
                        stateDelivery = "Yes";
                        break;
                    }
                }
                if (stateParking.equals("Yes")&&stateDelivery.equals("Yes")){
                    lbError.setText("Driver is in delivery");

                }else if (stateParking.equals("No")&&stateDelivery.equals("Yes")){
                    lbError.setText("");
                }else if (stateParking.equals("No")&&stateDelivery.equals("No")){
                    lbError.setText("Vehicle is in delivery");
                }else {
                    lbError.setText("");
                }
            }

        });

        //Clock
        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                    Calendar cal = Calendar.getInstance();

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    String state=null;
                    if(hour>=12){
                        state="PM";
                    }else{
                        state="AM";
                    }
                    time.setText("    "+String.format("%02d", hour)+ ":" +String.format("%02d", minute)+ ":" + String.format("%02d", second)+" "+state);

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };
        clock.start();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        lbdate.setText(dateFormat.format(date));
    }

    public void openLoginWindow(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        contexDash.getChildren().clear();
        contexDash.getChildren().add(load);
    }

    public void parkVehicle(ActionEvent actionEvent) {
        if (cmbDrivers.getSelectionModel().isEmpty()){
            lbError.setText("Driver Not Found");
            return;
        }else {

            for (Parking temp : parkingList) {
                if (cmbVehicle.getSelectionModel().getSelectedItem().equals(temp.getVehicleNumber())) {
                    lbError.setText("Vehicle is already exists");
                    return;
                }
            }

            try {
                deliveryList.removeIf(temp -> temp.getVehicleName().equals(cmbVehicle.getSelectionModel().getSelectedItem().toString()));
                parkingSlot[Integer.parseInt(slotlb.getText()) - 1][1] = cmbVehicle.getSelectionModel().getSelectedItem().toString();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
                Date date = new Date();

                Parking park = new Parking(cmbVehicle.getSelectionModel().getSelectedItem().toString(), lbtype.getText(), slotlb.getText(), formatter.format(date));
                parkingList.add(park);
            } catch (Exception e) {
                lbError.setText("Vehicle Not Available");
            }
        }

    }

    public void deliver(ActionEvent actionEvent) {
        boolean bool=true;

        if (cmbDrivers.getSelectionModel().isEmpty()){
            lbError.setText("Driver Not Found");
            return;
        }else {
            for (Delivery temp : deliveryList) {
                if (cmbVehicle.getSelectionModel().getSelectedItem().equals(temp.getVehicleName())){
                    lbError.setText("Vehicle is already exists");
                    return;
                }

            }

            try {
                parkingList.removeIf(temp -> temp.getVehicleNumber().equals(cmbVehicle.getSelectionModel().getSelectedItem().toString()));
                parkingSlot[Integer.parseInt(slotlb.getText()) - 1][1] = null;

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
                Date date = new Date();

                Delivery delivery = new Delivery(cmbVehicle.getSelectionModel().getSelectedItem().toString(), lbtype.getText(), cmbDrivers.getSelectionModel().getSelectedItem(), formatter.format(date));
                deliveryList.add(delivery);
            } catch (Exception e) {
                lbError.setText("Vehicle Not Available");
            }
        }
    }

    public void slot(){
        for (int i = 0; i <parkingSlot.length ; i++) {
            if (cmbVehicle.getSelectionModel().getSelectedItem().toString().equals(parkingSlot[i][1])){
                parking.setDisable(true);
                delivery.setDisable(false);
                slotlb.setText(String.format("%02d", i+1));
                return;

            }else {
                delivery.setDisable(true);
                parking.setDisable(false);
                switch (lbtype.getText()) {
                    case "Bus":
                        slotlb.setText("14");

                        break;
                    case "Van":
                        for (int j = 0; j < parkingSlot.length; j++) {
                            if (parkingSlot[j][0].equals("Van") && parkingSlot[j][1] == null) {
                                slotlb.setText(String.format("%02d", j + 1));
                                break;
                            }
                        }
                        break;
                    case "CargoLorry":
                        for (int k = 0; k < parkingSlot.length; k++) {
                            if (parkingSlot[k][0].equals("CargoLorry") && parkingSlot[k][1] == null) {
                                slotlb.setText(String.format("%02d", k + 1));
                                break;
                            }
                        }
                        break;
                }

            }
        }
    }
}




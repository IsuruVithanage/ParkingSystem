package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static controller.DashBoardController.parkingList;

public class AddVehicleController {
    public TextField vehicleNumber;
    public TextField weight;
    public TextField passengers;
    public JFXComboBox cmbvehicle;
    public String vehicleType;

    static ArrayList<Vehicle> vehicleList = new ArrayList<>();
    public Label lberror;
    public AnchorPane context;
    public Label accountNameadd;

    public void initialize(){
        accountNameadd.setText(LoginFormController.username);

        cmbvehicle.getItems().addAll(
                "Bus",
                "Van",
                "CargoLorry"
        );
        cmbvehicle.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            vehicleType=newValue.toString();
        });

       vehicleNumber.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!vehicleNumber.getText().matches("[A-Z]{2}[-]\\d{3}")){
                    vehicleNumber.setText("");
                }
                for (Vehicle temp:vehicleList
                ) {
                    if (vehicleNumber.getText().equals(temp.getVehicleNumber())){
                        vehicleNumber.setText("");
                    }
                }
            }



        });

        weight.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!weight.getText().matches("\\d+")){
                    weight.setText("");
                }
            }

        });

        passengers.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if(!passengers.getText().matches("\\d+")){
                    passengers.setText("");
                }
            }

        });

        vehicleNumber.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                weight.requestFocus();
            }
        });

        weight.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                passengers.requestFocus();
            }
        });

    }

    public void addNewVehicle(ActionEvent actionEvent) {
        String slot="14";
        if (vehicleNumber.getText() == null || vehicleNumber.getText().trim().isEmpty()) {
            lberror.setText("Vehicle Number is empty");
        }else if (weight.getText() == null || weight.getText().trim().isEmpty()){
            lberror.setText("\t\tWeight is empty");
        }else if (passengers.getText() == null || passengers.getText().trim().isEmpty()){
            lberror.setText("Passenger count is empty");
        }else if (cmbvehicle.getSelectionModel().isEmpty()){
            lberror.setText("Vehicle Type is empty");
        }else{
            Vehicle vehicle=new Vehicle(vehicleNumber.getText(),vehicleType,weight.getText(),passengers.getText());
            vehicleList.add(vehicle);
        }

        switch (vehicleType) {
            case "Bus":
                DashBoardController.parkingSlot[13][1]=vehicleNumber.getText();

                break;
            case "Van":
                for (int j = 0; j < DashBoardController.parkingSlot.length; j++) {
                    if (DashBoardController.parkingSlot[j][0].equals("Van") && DashBoardController.parkingSlot[j][1] == null) {
                        DashBoardController.parkingSlot[j][1]=vehicleNumber.getText();
                        slot= String.valueOf(j+1);
                        break;
                    }
                }
                break;
            case "CargoLorry":
                for (int k = 0; k < DashBoardController.parkingSlot.length; k++) {
                    if (DashBoardController.parkingSlot[k][0].equals("CargoLorry") && DashBoardController.parkingSlot[k][1] == null) {
                        DashBoardController.parkingSlot[k][1]=vehicleNumber.getText();
                        slot= String.valueOf(k+1);
                        break;
                    }
                }
                break;
        }


        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm a");
        Date date = new Date();

        Parking park = new Parking(vehicleNumber.getText(),vehicleType,slot, formatter.format(date));
        parkingList.add(park);

    }


    public void clearData(ActionEvent actionEvent) {
        vehicleNumber.clear();
        weight.clear();
        passengers.clear();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ParkingWindow.fxml"));
        Parent parent = loader.load();
        ParkingWindowController controller = (ParkingWindowController) loader.getController();
        controller.loadData();
        controller.setAccountName(LoginFormController.username);
        context.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Delivery;
import model.Parking;
import view.tm.DeliveryTM;
import view.tm.ParkingTM;

import java.io.IOException;
import java.net.URL;

import static controller.DashBoardController.deliveryList;
import static controller.DashBoardController.parkingList;

public class ParkingWindowController {
    public JFXComboBox cmbmain;
    public AnchorPane contextPark;
    public TableView vehicletbl;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colSlot;
    public TableColumn colTime;
    public Label accountName;

    public void initialize() throws IOException {
        cmbmain.getItems().addAll(
                "In Parking",
                "On Delivery"
        );
        cmbmain.getSelectionModel().selectFirst();

        cmbmain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("On Delivery")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeliveryWindow.fxml"));
                Parent parent = null;
                try {
                    parent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                DeliveryWindowController controller = (DeliveryWindowController) loader.getController();
                controller.loadData();
                controller.setAccountName(accountName.getText());
                contextPark.getScene().getWindow().hide();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        });
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    public void openAddvehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddVehicle.fxml"));
        Parent parent = loader.load();
        contextPark.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void openAddDriver(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddDrivers.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void logout(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        contextPark.getChildren().clear();
        contextPark.getChildren().add(load);
    }

    public void setAccountName(String name){
        accountName.setText(name);
    }

    public void openAllVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VehicleTable.fxml"));
        Parent parent = loader.load();
        VehicleTableController controller = (VehicleTableController) loader.getController();
        controller.loadData();
        contextPark.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void openAllDriver(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DriverTable.fxml"));
        Parent parent = loader.load();
        DriverTableController controller = (DriverTableController) loader.getController();
        controller.loadData();
        contextPark.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void loadData(){
        ObservableList<ParkingTM> tmObservableList = FXCollections.observableArrayList();

        for (Parking temp:parkingList
        ) {
            tmObservableList.add(new ParkingTM(temp.getVehicleNumber(),temp.getVehicleType(),temp.getSlot(),temp.getTime()));
        }

        vehicletbl.setItems(tmObservableList);

    }
}

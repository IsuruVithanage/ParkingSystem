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
import view.tm.DeliveryTM;

import java.io.IOException;
import java.net.URL;

import static controller.DashBoardController.deliveryList;

public class DeliveryWindowController {
    public JFXComboBox cmbDeliver;
    public AnchorPane contexDeliver;
    public TableColumn colvehicleNumber;
    public TableView<DeliveryTM> deliverytbl;
    public TableColumn colVehicleType;
    public TableColumn driverName;
    public TableColumn leftTime;
    public Label lbAccountName;

    public void initialize() throws IOException {
        cmbDeliver.getItems().addAll(
                "On Delivery",
                "In Parking"
        );
        cmbDeliver.getSelectionModel().selectFirst();

        cmbDeliver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("In Parking")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ParkingWindow.fxml"));
                Parent parent = null;
                try {
                    parent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ParkingWindowController controller = (ParkingWindowController) loader.getController();
                controller.loadData();
                contexDeliver.getScene().getWindow().hide();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        });
        colvehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        driverName.setCellValueFactory(new PropertyValueFactory<>("driveerName"));
        leftTime.setCellValueFactory(new PropertyValueFactory<>("leftTime"));
    }

    public void openAddVehicle(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddVehicle.fxml"));
        Scene scene = new Scene(load);
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

    public void setAccountName(String name){
        lbAccountName.setText(name);
    }

    public void openAllVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/VehicleTable.fxml"));
        Parent parent = loader.load();
        VehicleTableController controller = (VehicleTableController) loader.getController();
        controller.loadData();
        contexDeliver.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void openAllDrivers(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DriverTable.fxml"));
        Parent parent = loader.load();
        DriverTableController controller = (DriverTableController) loader.getController();
        controller.loadData();
        contexDeliver.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void loadData(){
        ObservableList<DeliveryTM> tmObservableList = FXCollections.observableArrayList();

        for (Delivery temp:deliveryList
             ) {
            tmObservableList.add(new DeliveryTM(temp.getVehicleName(),temp.getVehicleType(),temp.getDriveerName(),temp.getLeftTime()));
        }

        deliverytbl.setItems(tmObservableList);

    }
}

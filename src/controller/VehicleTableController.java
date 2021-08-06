package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Delivery;
import model.Parking;
import model.Vehicle;
import view.tm.VehicleTM;

import java.io.IOException;
import java.net.URL;

import static controller.AddVehicleController.vehicleList;
import static controller.DashBoardController.deliveryList;
import static controller.DashBoardController.parkingList;

public class VehicleTableController {
    public AnchorPane contextvtm;
    public TableView<VehicleTM> allVehicletbl;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colMaxWeight;
    public TableColumn colNoPassenger;
    public TableColumn colDelete;

    public void initialize(){
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colMaxWeight.setCellValueFactory(new PropertyValueFactory<>("maxweight"));
        colNoPassenger.setCellValueFactory(new PropertyValueFactory<>("noOfPassenger"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ParkingWindow.fxml"));
        Parent parent = loader.load();
        ParkingWindowController controller = (ParkingWindowController) loader.getController();
        controller.loadData();
        contextvtm.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void loadData(){
        ObservableList<VehicleTM> tmObservableList = FXCollections.observableArrayList();
        for (Vehicle temp:vehicleList
        ) {
            Button btn=new Button("Delete");
            tmObservableList.add(new VehicleTM(temp.getVehicleNumber(),temp.getVehicleType(),temp.getMaxweight(),temp.getNoOfPassenger(),btn));

            btn.setOnAction((e)->{
                parkingList.removeIf(temp1 -> temp.getVehicleNumber().equals(temp1.getVehicleNumber()));
                deliveryList.removeIf(temp2 -> temp.getVehicleNumber().equals(temp2.getVehicleName()));
                vehicleList.remove(temp);
                loadData();
            });

        }
        allVehicletbl.setItems(tmObservableList);

    }
}

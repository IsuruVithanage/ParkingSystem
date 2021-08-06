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
import model.Driver;
import view.tm.DriverTM;
import view.tm.VehicleTM;

import java.io.IOException;
import java.net.URL;

import static controller.AddDriversController.driverList;

public class DriverTableController {
    public AnchorPane contextdtm;
    public TableColumn colname;
    public TableColumn colNIC;
    public TableColumn colLisenceNo;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDelete;
    public TableView<DriverTM> drivertbl;

    public void initialize(){
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colLisenceNo.setCellValueFactory(new PropertyValueFactory<>("lisenceNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ParkingWindow.fxml"));
        Parent parent = loader.load();
        ParkingWindowController controller = (ParkingWindowController) loader.getController();
        controller.loadData();
        contextdtm.getScene().getWindow().hide();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void loadData(){
        ObservableList<DriverTM> tmObservableList = FXCollections.observableArrayList();
        for (Driver temp:driverList
        ) {
            Button btn=new Button("Delete");
            tmObservableList.add(new DriverTM(temp.getName(),temp.getNic(),temp.getLisenceNo(),temp.getAddress(),temp.getContact(),btn));

            btn.setOnAction((e)->{
                driverList.remove(temp);
                loadData();
            });

        }
        drivertbl.setItems(tmObservableList);

    }
}

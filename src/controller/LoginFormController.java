package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginFormController {
    public AnchorPane contextLog;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public Label lbError;
    public static String username="";

    public void openParkingWindow(ActionEvent actionEvent) throws IOException {
        username=txtUsername.getText().toUpperCase();
        if (txtPassword.getText().equals("123")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ParkingWindow.fxml"));
            Parent parent = loader.load();
            ParkingWindowController controller = (ParkingWindowController) loader.getController();
            controller.loadData();
            controller.setAccountName("MR " + username);
            contextLog.getScene().getWindow().hide();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else {
            lbError.setText("Incorrect Password");
        }

    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        contextLog.getChildren().clear();
        contextLog.getChildren().add(load);
    }
}

package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class LoadWindowController {
    public AnchorPane contextLoad;

    public void initialize() throws IOException {
        try {
            sleep(15000);
            openWin();
        } catch (InterruptedException ex) {

        }

        /*URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        contextLoad.getChildren().clear();
        contextLoad.getChildren().add(load);*/
    }

    public void openWin() throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        contextLoad.getChildren().clear();
        contextLoad.getChildren().add(load);
    }
}

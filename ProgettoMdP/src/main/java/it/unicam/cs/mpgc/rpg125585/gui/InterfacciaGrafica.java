package it.unicam.cs.mpgc.rpg125585.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class InterfacciaGrafica extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu_principale.fxml"));
        Parent root = loader.load();

        stage.setTitle("MdP RPG Game");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
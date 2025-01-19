package com.adsabri.wordvaultbp2;

import com.adsabri.wordvaultbp2.pages.LoginPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        LoginPage loginPage = new LoginPage(stage);

        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

// vragen of de textpane achter de loginpane zit of dat er iets anders aan de hand is
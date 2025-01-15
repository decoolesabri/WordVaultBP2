package com.adsabri.wordvaultbp2.pages;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginPage {

    public LoginPage (Stage loginStage) {

        System.out.println("Login Page");

        GridPane rootPain = new GridPane();
        rootPain.setStyle("-fx-background-color: lightblue");

        Scene scene = new Scene(rootPain, 1400, 750);

        loginStage.setScene(scene);
    }
}

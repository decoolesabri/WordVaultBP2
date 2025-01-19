package com.adsabri.wordvaultbp2.pages;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    private Pane rootPain;
    private Scene scene;
    private FlowPane textPane;
    private FlowPane loginPane;

    public LoginPage (Stage loginStage) {
        System.out.println("Login Page");

        rootPain = new Pane();
        scene = new Scene(rootPain, 1400, 750);

        setupLayout();


        loginStage.setScene(scene);
        loginStage.setTitle("Login Page");
    }

    public void setupLayout () {
        System.out.println("Setup Layout");

        // init van de layout panes
        loginPane = new FlowPane();
        textPane = new FlowPane();

        // size van de layout panes
        loginPane.setPrefSize(200, 370);
        textPane.setPrefSize(200, 370);

        // toevoegen stylen
        applyStyling();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void applyStyling () {
        rootPain.setStyle("-fx-background-color: lightblue");
        loginPane.setStyle("-fx-background-color: red");
        textPane.setStyle("-fx-background-color: yellow");
    }

    private void addChildren () {
        rootPain.getChildren().add(textPane);
        rootPain.getChildren().add(loginPane);
    }


}

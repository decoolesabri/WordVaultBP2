package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    private FlowPane rootPane;
    private Scene scene;
    private FlowPane textPane;
    private FlowPane loginPane;
    private ImageView backgroundImage;

    public LoginPage (Stage loginStage) {
        System.out.println("Login Page");

        rootPane = new FlowPane();
        scene = new Scene(rootPane, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/loginpage.css").toString());

        setupLayout();

        loginStage.setScene(scene);
        loginStage.setTitle("Login Page");
    }

    public void setupLayout () {
        System.out.println("Setup Layout");

        // init's
        loginPane = new FlowPane();
        textPane = new FlowPane();

        Image lBackground = new Image(Application.class.getResource("images/l-background.jpg").toString());
        backgroundImage = new ImageView();
        backgroundImage.setImage(lBackground);

        // id's geven aan de layout panes
        rootPane.setId("rootPane");
        loginPane.setId("loginPane");
        textPane.setId("textPane");

        // sizes
        loginPane.setPrefSize(200, 370);
        textPane.setPrefSize(200, 370);
        backgroundImage.setFitWidth(1400);
        backgroundImage.setFitHeight(750);

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void addChildren () {
        rootPane.getChildren().add(textPane);
        rootPane.getChildren().add(loginPane);
        rootPane.getChildren().add(backgroundImage);
    }



}

// vragen of het mogelijk is om een achtergrondfoto te zetten als je een FlowPane gebruikt
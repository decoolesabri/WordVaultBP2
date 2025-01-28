package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {

    private Pane root;
    private Scene scene;
    private VBox leftPane;
    private VBox rightPane;
    private ImageView backgroundImage;

    public HomePage (Stage homeStage) {

        System.out.println("Login Page");

        root = new Pane();
        scene = new Scene(root, 1400, 750);
//        scene.getStylesheets().add(Application.class.getResource("stylesheets/loginpage.css").toString());

        setupLayout();

        homeStage.setScene(scene);
        homeStage.setTitle("Login Page");

    }

    public void setupLayout () {

    }

    public void addChildren () {

    }

}

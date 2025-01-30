package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {

    private Pane root;
    private Scene scene;
    private VBox textPane;
    private VBox loginPane;
    private ImageView backgroundImage;
    private Label loginLabel;
    private Label appLabel;
    private Label sloganLabel;
    private TextField textUsername;
    private TextField textPassword;
    private Button loginButton;

    public LoginPage (Stage loginStage) {

        System.out.println("Login Page");

        root = new Pane();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/loginpage.css").toString());

        setupLayout();

        loginStage.setScene(scene);
        loginStage.setTitle("Login Page");

    }

    public void setupLayout () {

        System.out.println("Setup Layout");

        // init's
        loginPane = new VBox();
        textPane = new VBox();

        Image lBackground = new Image(Application.class.getResource("images/l-background.jpg").toString());
        backgroundImage = new ImageView();
        backgroundImage.setImage(lBackground);

        appLabel = new Label("WordVault Spanish");
        sloganLabel = new Label("Your personal dictionary for mastering Spanish!");
        loginLabel = new Label("User login");

        textUsername = new TextField();
        textUsername.setPromptText("Username");

        textPassword = new TextField();
        textPassword.setPromptText("Password");

        loginButton = new Button("Login");

        // id's geven aan de layout panes
        root.setId("rootPane");
        loginPane.setId("loginPane");
        textPane.setId("textPane");

        // sizes
        loginPane.setPrefSize(400, 470);
        textPane.setPrefSize(370, 200);

        backgroundImage.setFitWidth(1400);
        backgroundImage.setFitHeight(750);

        textUsername.setMaxWidth(300);
        textPassword.setMaxWidth(300);

        // plaats panes
        loginPane.setLayoutX(900);
        loginPane.setLayoutY(150);
        textPane.setLayoutX(200);
        textPane.setLayoutY(100);

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void addChildren () {
        root.getChildren().add(backgroundImage);
        root.getChildren().add(textPane);
        root.getChildren().add(loginPane);

        textPane.getChildren().addAll(appLabel, sloganLabel);
        loginPane.getChildren().addAll(loginLabel, textUsername, textPassword, loginButton);
    }



}


package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.controllers.LoginController;
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

    private void setupLayout () {

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

        // set on action koppelen
        setOnAction();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void setOnAction () {

        loginButton.setOnAction(event -> {

            String username = textUsername.getText();
            String password = textPassword.getText();

            LoginController loginController = new LoginController();
            boolean loginSuccess = loginController.handleLogin(username, password);

            // Toon succesmelding en knop als login correct is
            if (loginSuccess) {
                showSuccessMessage();
            }

        });

    }

    private void showSuccessMessage() {

        Label successLabel = new Label("Login succesvol!");
        successLabel.setId("successLabel");

        Button continueButton = new Button("Doorgaan");
        continueButton.setId("continueButton");

        continueButton.setOnAction(e -> {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            HomePage homePage = new HomePage(stage);
            stage.setScene(homePage.getScene());
        });

        // Voeg de melding en knop toe aan de loginPane
        loginPane.getChildren().addAll(successLabel, continueButton);

    }

    private void addChildren() {
        root.getChildren().add(backgroundImage);
        root.getChildren().add(textPane);
        root.getChildren().add(loginPane);

        textPane.getChildren().addAll(appLabel, sloganLabel);
        loginPane.getChildren().addAll(loginLabel, textUsername, textPassword, loginButton);
    }

}


package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.controllers.LoginController;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.util.Duration;

public class LoginPage {

    private Pane root;
    private Scene scene;
    private VBox textPane;
    private VBox loginPane;
    private Image lBackground;
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

        lBackground = new Image(Application.class.getResource("images/l-background.jpg").toString());
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

        FadeTransition fade = new FadeTransition(Duration.seconds(1), loginPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        // id's geven aan de onderdelen
        root.setId("rootPane");
        loginPane.setId("loginPane");
        textPane.setId("textPane");

        appLabel.setId("appLabel");
        sloganLabel.setId("sloganLabel");
        loginLabel.setId("loginLabel");

        textUsername.setId("textUsername");
        textPassword.setId("textPassword");

        loginButton.setId("loginButton");

        // sizes
        loginPane.setPrefSize(400, 470);
        textPane.setPrefSize(510, 200);

        backgroundImage.setFitWidth(1400);
        backgroundImage.setFitHeight(750);

        textUsername.setMaxWidth(300);
        textPassword.setMaxWidth(300);

        // plaats panes
        loginPane.setLayoutX(900);
        loginPane.setLayoutY(150);
        textPane.setLayoutX(200);
        textPane.setLayoutY(100);

        // Login Pane instellingen
        loginPane.setSpacing(30);
        loginPane.setPadding(new Insets(20));
        loginPane.setAlignment(Pos.CENTER);

        // Text Pane instellingen
        textPane.setSpacing(10);
        textPane.setPadding(new Insets(15));
        textPane.setAlignment(Pos.CENTER);

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


package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.controllers.LoginController;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class LoginPage {

    private Pane root;
    private Scene scene;
    private LoginController loginController;
    private VBox textPane;
    private VBox loginPane;
    private Image lBackground;
    private ImageView backgroundImage;
    private Label loginLabel;
    private Label appLabel;
    private Label sloganLabel;
    private Label errorLabel;
    private TextField textUsername;
    private PasswordField textPassword;
    private Button loginButton;
    private double screenWidth;
    private double screenHeight;

    public LoginPage (Stage loginStage, LoginController loginController) {

        // Logincontroller instellen
        this.loginController = loginController;

        // Scherm groottes krijgen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        this.screenWidth = primaryScreenBounds.getWidth();
        this.screenHeight = primaryScreenBounds.getHeight();

        // Maak de root en scene op basis van het schermformaat
        root = new Pane();
        scene = new Scene(root, screenWidth, screenHeight);  // Stel de scene in op full-screen
        scene.getStylesheets().add(Application.class.getResource("stylesheets/loginpage.css").toString());

        setupLayout();

        loginStage.setFullScreen(true);

        loginStage.setScene(scene);
        loginStage.setTitle("Login Page");

    }

    private void setupLayout () {

        // Init's
        loginPane = new VBox();
        textPane = new VBox();

        lBackground = new Image(Application.class.getResource("images/l-background.jpg").toString());
        backgroundImage = new ImageView();
        backgroundImage.setImage(lBackground);

        appLabel = new Label("WordVault Spanish");
        sloganLabel = new Label("Your personal dictionary for mastering Spanish!");
        loginLabel = new Label("User login");
        errorLabel = new Label("Vul beide velden in.");
        errorLabel.setVisible(false);

        textUsername = new TextField();
        textUsername.setPromptText("Username");

        textPassword = new PasswordField();
        textPassword.setPromptText("Password");

        loginButton = new Button("Login");

        // Animatie instellen voor de loginPane
        FadeTransition fade = new FadeTransition(Duration.seconds(1), loginPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        // Id's geven aan de onderdelen
        root.setId("rootPane");
        loginPane.setId("loginPane");
        textPane.setId("textPane");

        appLabel.setId("appLabel");
        sloganLabel.setId("sloganLabel");
        loginLabel.setId("loginLabel");
        errorLabel.setId("errorLabel");

        textUsername.setId("textUsername");
        textPassword.setId("textPassword");

        loginButton.setId("loginButton");

        // Sizes
        loginPane.setPrefSize(400, 470);
        textPane.setPrefSize(510, 200);

        backgroundImage.setFitWidth(screenWidth);
        backgroundImage.setFitHeight(screenHeight);

        textUsername.setMaxWidth(300);
        textPassword.setMaxWidth(300);

        // Plaats panes
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

            boolean loginSuccess = loginController.handleLogin(username, password);

            // Toon succesmelding en knop als login correct is
            if (loginSuccess) {
                errorLabel.setVisible(false);
                showSuccessMessage();
            } else {
                errorLabel.setVisible(true);
            }

        });

    }

    private void showSuccessMessage() {

        Label successLabel = new Label("Login succesvol!");
        successLabel.setId("successLabel");

        // Button om door te gaan naar de HomePage
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

    public Scene getScene() {
        return scene;
    }

    private void addChildren() {
        root.getChildren().add(backgroundImage);
        root.getChildren().add(textPane);
        root.getChildren().add(loginPane);

        textPane.getChildren().addAll(appLabel, sloganLabel);
        loginPane.getChildren().addAll(loginLabel, textUsername, textPassword, loginButton, errorLabel);
    }

}


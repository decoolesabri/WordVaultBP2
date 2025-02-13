package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.controllers.CreateController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePage {

    private Stage homeStage;
    private Pane root;
    private Scene scene;
    private VBox topPane;
    private VBox leftPane;
    private VBox rightPane;
    private VBox bottomPane;
    private Image hBackground;
    private ImageView backgroundImage;
    private Label topLabel;
    private Label leftLabel;
    private Button addButton;
    private Button listButton;
    private Button logoutButton;

    public HomePage (Stage homeStage) {

        this.homeStage = homeStage;

        root = new Pane();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/homepage.css").toString());

        setupLayout();

        homeStage.setScene(scene);
        homeStage.setTitle("Home Page");

    }

    private void setupLayout () {

        // init's
        topPane = new VBox();
        leftPane = new VBox();
        rightPane = new VBox();
        bottomPane = new VBox();

        hBackground = new Image(Application.class.getResource("images/h-background.jpg").toString());
        backgroundImage = new ImageView();
        backgroundImage.setImage(hBackground);

        backgroundImage.setFitWidth(1400);
        backgroundImage.setFitHeight(750);

        backgroundImage.setPreserveRatio(false);

        topLabel = new Label("Welcome, 'Username'");
        leftLabel = new Label("A powerfull tool to create and manage your personalized wordlist, store translations and notes, and built your language skills step by step in an organized way!");

        addButton = new Button("Add word!");
        listButton = new Button("View word list");
        logoutButton = new Button("Logout");

        // id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        bottomPane.setId("bottomPane");

        topLabel.setId("topLabel");
        leftLabel.setId("leftLabel");

        addButton.setId("leftButton");
        listButton.setId("rightButton");
        logoutButton.setId("bottomButton");

        // sizes
        topPane.setPrefSize(430, 100);
        leftPane.setPrefSize(300, 500);
        rightPane.setPrefSize(600, 500);
        bottomPane.setPrefSize(125, 75);

        // plaats panes
        topPane.setLayoutX(500);
        topPane.setLayoutY(40);

        leftPane.setLayoutX(70);
        leftPane.setLayoutY(100);

        rightPane.setLayoutX(750);
        rightPane.setLayoutY(200);

        bottomPane.setLayoutX(70);
        bottomPane.setLayoutY(650);

        leftPane.setSpacing(20);
        leftPane.setPadding(new Insets(15));
        leftPane.setAlignment(Pos.CENTER);

        // set on action koppelen
        setOnAction();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void setOnAction () {

        addButton.setOnAction(event -> {
            Stage addStage = new Stage();
            Database db = new Database();
            CreateController cc = new CreateController(db);
            new AddPage(addStage, cc);
            addStage.show();

//            AddPage addPage = new AddPage(homeStage);
//            homeStage.setScene(addPage.getScene());
        });

        listButton.setOnAction(event -> {
            ListPage listPage = new ListPage(homeStage);
            homeStage.setScene(listPage.getScene());
        });

        logoutButton.setOnAction(event -> {
            LoginPage loginPage = new LoginPage(homeStage);
            homeStage.setScene(loginPage.getScene());
        });

    }

    public Scene getScene() {
        return scene;
    }

    public void addChildren () {

        root.getChildren().add(backgroundImage);
        root.getChildren().addAll(topPane, leftPane, rightPane, bottomPane);
        topPane.getChildren().add(topLabel);
        leftPane.getChildren().addAll(leftLabel, addButton);
        rightPane.getChildren().add(listButton);
        bottomPane.getChildren().add(logoutButton);

    }

}

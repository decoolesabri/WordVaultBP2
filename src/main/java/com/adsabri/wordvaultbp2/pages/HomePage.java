package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        System.out.println("Setup Layout");

        // init's
        topPane = new VBox();
        leftPane = new VBox();
        rightPane = new VBox();
        bottomPane = new VBox();

        topLabel = new Label("Welcome, 'Username'");
        leftLabel = new Label("A powerfull tool to create and manage your personalized wordlist, store translations and notes, and built your language skills step by step in an organized way!");

        addButton = new Button("Left Page");
        listButton = new Button("Right Page");
        logoutButton = new Button("Logout");

        // id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        bottomPane.setId("bottomPane");

        topLabel.setId("topPane-text");
        leftLabel.setId("leftPane-text");

        addButton.setId("leftPane-button");
        listButton.setId("rightPane-button");
        logoutButton.setId("bottomPane-button");

        // sizes
        topPane.setPrefSize(400, 100);
        leftPane.setPrefSize(300, 500);
        rightPane.setPrefSize(300, 500);
        bottomPane.setPrefSize(125, 75);

        // plaats panes
        topPane.setLayoutX(500);
        topPane.setLayoutY(40);

        leftPane.setLayoutX(70);
        leftPane.setLayoutY(100);

        rightPane.setLayoutX(1000);
        rightPane.setLayoutY(200);

        bottomPane.setLayoutX(70);
        bottomPane.setLayoutY(650);

        // set on action koppelen
        setOnAction();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void setOnAction () {

        addButton.setOnAction(event -> {
            AddPage addPage = new AddPage(homeStage);
            homeStage.setScene(addPage.getScene());
        });

        listButton.setOnAction(event -> {
            ListPage listPage = new ListPage(homeStage);
            homeStage.setScene(listPage.getScene());
        });

        logoutButton.setOnAction(event -> {

        });

    }

    public Scene getScene() {
        return scene;
    }

    public void addChildren () {
        root.getChildren().addAll(topPane, leftPane, rightPane, bottomPane);
        topPane.getChildren().add(topLabel);
        leftPane.getChildren().addAll(leftLabel, addButton);
        rightPane.getChildren().add(listButton);
        bottomPane.getChildren().add(logoutButton);

    }

}

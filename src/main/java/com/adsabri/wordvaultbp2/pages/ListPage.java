package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListPage {

    private Stage stage;
    private Pane root;
    private Scene scene;
    private VBox topPane;
    private VBox listPane;
    private VBox buttonPane;
    private Label label;
    private Button homeButton;
    private Button addButton;

    public ListPage (Stage stage) {

        this.stage = stage;

        root = new Pane();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/listpage.css").toString());

        setupLayout();

        stage.setScene(scene);
        stage.setTitle("List Page");

    }

    private void setupLayout () {

        System.out.println("Setup Layout");

        // init's
        topPane = new VBox();
        listPane = new VBox();
        buttonPane = new VBox();

        label = new Label("Current wordlist");

        homeButton = new Button("Back");
        addButton = new Button("Add");

        // id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        listPane.setId("listPane");
        buttonPane.setId("buttonPane");

        label.setId("label");

        homeButton.setId("backButton");
        addButton.setId("addButton");

        // sizes
        topPane.setPrefSize(500, 100);
        listPane.setPrefSize(850, 425);
        buttonPane.setPrefSize(1400, 50);

        // plaats panes
        topPane.setLayoutX(450);
        topPane.setLayoutY(40);

        listPane.setLayoutX(275);
        listPane.setLayoutY(200);

        buttonPane.setLayoutX(0);
        buttonPane.setLayoutY(650);

        // set on action koppelen
        setOnAction();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void setOnAction () {

        homeButton.setOnAction(e -> {
            stage.setScene(new HomePage(stage).getScene());
        });

        addButton.setOnAction(e -> {
            AddPage addPage = new AddPage(stage);
            stage.setScene(addPage.getScene());
        });

    }

    public Scene getScene() {
        return scene;
    }

    private void addChildren () {
        root.getChildren().addAll(topPane, listPane, buttonPane);
        topPane.getChildren().addAll(label);
        listPane.getChildren().addAll();
        buttonPane.getChildren().addAll(homeButton, addButton);
    }

}

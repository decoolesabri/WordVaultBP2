package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPage {

    private Pane root;
    private Scene scene;
    private VBox topPane;
    private VBox middlePane;
    private VBox savePane;
    private VBox buttonPane;
    private Label label;
    private TextField word;
    private TextField meaning;
    private TextField note;
    private Button saveButton;
    private Button backButton;
    private Button listButton;

    public AddPage (Stage AddPage) {

        System.out.println("Add Page");

        root = new Pane();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/addpage.css").toString());

        setupLayout();

        AddPage.setScene(scene);
        AddPage.setTitle("Add Page");

    }

    private void setupLayout () {

        System.out.println("Setup Layout");

        // init's
        topPane = new VBox();
        middlePane = new VBox();
        savePane = new VBox();
        buttonPane = new VBox();

        label = new Label("Add your new learned word here!");

        word = new TextField();
        meaning = new TextField();
        note = new TextField();
        word.setPromptText("Enter Spanish word");
        meaning.setPromptText("Enter translation");
        note.setPromptText("Enter note or example sentence");

        saveButton = new Button("Save");
        backButton = new Button("Back");
        listButton = new Button("List");

        // id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        middlePane.setId("middlePane");
        savePane.setId("savePane");
        buttonPane.setId("buttonPane");

        label.setId("label");
        word.setId("word");
        meaning.setId("meaning");
        note.setId("note");

        saveButton.setId("saveButton");
        backButton.setId("backButton");
        listButton.setId("listButton");

        // sizes
        topPane.setPrefSize(500, 100);
        middlePane.setPrefSize(600, 300);
        savePane.setPrefSize(100, 100);
        buttonPane.setPrefSize(1400, 50);

        // plaats panes
        topPane.setLayoutX(450);
        topPane.setLayoutY(40);

        middlePane.setLayoutX(400);
        middlePane.setLayoutY(200);

        savePane.setLayoutX(650);
        savePane.setLayoutY(500);

        buttonPane.setLayoutX(0);
        buttonPane.setLayoutY(650);

        // set on action koppelen
        setOnAction();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void setOnAction () {

        saveButton.setOnAction(e -> {

        });

        backButton.setOnAction(e -> {

        });

        listButton.setOnAction(e -> {

        });

    }

    private void addChildren () {
        root.getChildren().addAll(topPane, middlePane, savePane, buttonPane);
        topPane.getChildren().addAll(label);
        middlePane.getChildren().addAll(word, meaning, note);
        savePane.getChildren().addAll(saveButton);
        buttonPane.getChildren().addAll(backButton, listButton);
    }

}

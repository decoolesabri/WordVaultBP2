package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.controllers.CreateController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPage {

    private Stage stage;
    private Scene scene;
    private Pane root;
    private VBox topPane;
    private VBox middlePane;
    private VBox savePane;
    private VBox buttonPane;
    private Label label;
    private TextField textWord;
    private TextField textMeaning;
    private TextField textNote;
    private Button saveButton;
    private Button homeButton;
    private Button listButton;

    public AddPage (Stage stage) {

        this.stage = stage;

        root = new Pane();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/addpage.css").toString());

        setupLayout();

        this.stage.setScene(scene);
        this.stage.setTitle("Add Page");

    }

    private void setupLayout () {

        // init's
        topPane = new VBox();
        middlePane = new VBox();
        savePane = new VBox();
        buttonPane = new VBox();

        label = new Label("Add your new learned word here!");

        textWord = new TextField();
        textMeaning = new TextField();
        textNote = new TextField();
        textWord.setPromptText("Enter Spanish word");
        textMeaning.setPromptText("Enter translation");
        textNote.setPromptText("Enter note or example sentence");

        saveButton = new Button("Save");
        homeButton = new Button("Back");
        listButton = new Button("List");

        // id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        middlePane.setId("middlePane");
        savePane.setId("savePane");
        buttonPane.setId("buttonPane");

        label.setId("label");
        textWord.setId("word");
        textMeaning.setId("meaning");
        textNote.setId("note");

        saveButton.setId("saveButton");
        homeButton.setId("backButton");
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

            String word = textWord.getText();
            String meaning = textMeaning.getText();
            String note = textNote.getText();

            CreateController createController = new CreateController();
            boolean createSuccess = createController.handleCreate(word, meaning, note);

            if (createSuccess) {
                showSuccessMessage();
            }

        });

        homeButton.setOnAction(e -> {
            HomePage homePage = new HomePage(stage);
            stage.setScene(homePage.getScene());
        });

        listButton.setOnAction(e -> {
            ListPage listPage = new ListPage(stage);
            stage.setScene(listPage.getScene());
        });

    }

    private void showSuccessMessage() {

        Label successLabel = new Label("Opslaan succesvol!");
        successLabel.setId("successLabel");

        // Voeg de melding en knop toe aan de loginPane
        middlePane.getChildren().addAll(successLabel);

        // Kijken of dit werkt

    }

    public Scene getScene() {
        return scene;
    }

    private void addChildren () {
        root.getChildren().addAll(topPane, middlePane, savePane, buttonPane);
        topPane.getChildren().addAll(label);
        middlePane.getChildren().addAll(textWord, textMeaning, textNote);
        savePane.getChildren().addAll(saveButton);
        buttonPane.getChildren().addAll(homeButton, listButton);
    }

}

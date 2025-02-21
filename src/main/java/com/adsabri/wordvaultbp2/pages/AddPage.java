package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.controllers.CategoryController;
import com.adsabri.wordvaultbp2.controllers.CreateController;
import com.adsabri.wordvaultbp2.controllers.UpdateController;
import com.adsabri.wordvaultbp2.controllers.LoginController;
import com.adsabri.wordvaultbp2.models.Word;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddPage {

    private Stage stage;
    private Scene scene;
    private CreateController cc;
    private UpdateController uc;
    private LoginController lc;
    private CategoryController catc;
    private Word selectedWord;
    private Pane root;
    private VBox topPane;
    private VBox middlePane;
    private VBox savePane;
    private HBox buttonPane;
    private Label topLabel;
    private TextField textWord;
    private TextField textMeaning;
    private TextArea textNote;
    private ComboBox<String> categoryBox;
    private Button saveButton;
    private Button homeButton;
    private Button listButton;

    public AddPage (Stage stage, CreateController cc, UpdateController uc, LoginController lc, CategoryController catc, Word word) {

        // Verschillende controllers instellen
        this.stage = stage;
        this.cc = cc;
        this.uc = uc;
        this.lc = lc;
        this.catc = catc;
        this.selectedWord = word;

        root = new Pane();
        scene = new Scene(root, 600, 700);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/addpage.css").toString());

        setupLayout();

        // Checken of een woord al aanwezig is om te kijken of het een edit is of niet
        if (word != null) {
            textWord.setText(word.getWord());
            textMeaning.setText(word.getMeaning());
            textNote.setText(word.getNote());
        }

        this.stage.setScene(scene);
        this.stage.setTitle("Add Page");
        this.stage.setResizable(false);

    }

    private void setupLayout () {

        // Init's
        topPane = new VBox();
        middlePane = new VBox();
        savePane = new VBox();
        buttonPane = new HBox();
        buttonPane.setSpacing(350);

        topLabel = new Label("Add your new learned word here!");

        textWord = new TextField();
        textMeaning = new TextField();
        textNote = new TextArea();
        textWord.setPromptText("Enter Spanish word");
        textMeaning.setPromptText("Enter translation");
        textNote.setPromptText("Enter note or example sentence");
        textNote.setPrefHeight(100);

        // Combobox met data uit de database
        categoryBox = new ComboBox();
        categoryBox.getItems().addAll(catc.getCategories());

        saveButton = new Button("Save");
        homeButton = new Button("Home");
        listButton = new Button("List");

        // Id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        middlePane.setId("middlePane");
        savePane.setId("savePane");
        buttonPane.setId("buttonPane");

        topLabel.setId("label");
        textWord.setId("word");
        textMeaning.setId("meaning");
        textNote.setId("note");

        saveButton.setId("saveButton");
        homeButton.setId("backButton");
        listButton.setId("listButton");

        // Sizes
        topPane.setPrefSize(500, 10);
        middlePane.setPrefSize(500, 300);
        savePane.setPrefSize(100, 60);
        buttonPane.setPrefSize(600, 50);

        // Plaats panes
        topPane.setLayoutX(45);
        topPane.setLayoutY(20);

        middlePane.setLayoutX(46);
        middlePane.setLayoutY(200);

        savePane.setLayoutX(267);
        savePane.setLayoutY(530);

        buttonPane.setLayoutX(0);
        buttonPane.setLayoutY(600);

        // set on action koppelen
        setOnAction();

        // toevoegen elementen aan parent layout
        addChildren();

    }

    private void setOnAction () {

        saveButton.setOnAction(e -> {

            // Ingevoerde/geselecteerde gegevens ophalen
            String word = textWord.getText();
            String meaning = textMeaning.getText();
            String note = textNote.getText();
            String selectedCategory = categoryBox.getValue();

            // Haal de categoryId op als een categorie geselecteerd is
            Integer categoryId = null;
            if (selectedCategory != null) {
                categoryId = catc.getCategoryId(selectedCategory);
            }

            if (selectedWord == null) {
                // Als er geen geselecteerd woord is, sla dan het nieuwe woord op met de gekozen categorie
                cc.save(word, meaning, note, selectedCategory);
            } else {
                // Als er een geselecteerd woord is, werk het woord bij met de nieuwe gegevens en de categorie
                selectedWord.setWord(word);
                selectedWord.setMeaning(meaning);
                selectedWord.setNote(note);
                uc.updateWord(selectedWord, categoryId);
            }

            showSuccessMessage();

        });


        homeButton.setOnAction(e -> {

            // Sluit de huidige AddPage
            stage.close();

            // Maak een nieuwe Stage voor de HomePage
            Stage newStage = new Stage();

            // Maak een nieuwe HomePage aan en geef de nieuwe Stage door
            HomePage homePage = new HomePage(newStage);

            // Zet de scene voor de nieuwe Stage naar de scene van de HomePage
            newStage.setScene(homePage.getScene());

            // Maak het nieuwe Stage zichtbaar
            newStage.show();

        });


        listButton.setOnAction(e -> {

            stage.close();

            Stage newStage = new Stage();

            ListPage listPage = new ListPage(newStage);

            newStage.setScene(listPage.getScene());

            newStage.show();

        });


    }

    private void showSuccessMessage() {

        // Controleer of er al een melding bestaat en verwijder deze
        middlePane.getChildren().removeIf(node -> node instanceof Label && "successLabel".equals(node.getId()));

        // Maak een nieuwe succesmelding aan
        Label successLabel = new Label("Save successful! Reopen the list page.");
        successLabel.setId("successLabel");

        // Voeg het label toe aan de savePane
        middlePane.getChildren().add(successLabel);

    }

    public Scene getScene() {
        return scene;
    }

    private void addChildren () {
        root.getChildren().addAll(topPane, middlePane, savePane, buttonPane);
        topPane.getChildren().addAll(topLabel);
        middlePane.getChildren().addAll(textWord, textMeaning, textNote, categoryBox);
        savePane.getChildren().addAll(saveButton);
        buttonPane.getChildren().addAll(homeButton, listButton);
    }

}

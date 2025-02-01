package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public void setupLayout () {

        System.out.println("Setup Layout");

        // init's

        // id's geven aan de onderdelen

        // sizes

        // plaats panes

        // toevoegen elementen aan parent layout

        addChildren();

    }

    public void addChildren () {

    }


}

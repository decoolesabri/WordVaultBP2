package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListPage {

    private Pane root;
    private Scene scene;
    private VBox topPane;
    private VBox listPane;
    private VBox buttonPane;
    private Button backButton;
    private Button addButton;

    public ListPage (Stage ListPage) {

        System.out.println("List Page");

        root = new Pane();
        scene = new Scene(root, 1400, 750);
        scene.getStylesheets().add(Application.class.getResource("stylesheets/listpage.css").toString());

        setupLayout();

        ListPage.setScene(scene);
        ListPage.setTitle("Add Page");

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

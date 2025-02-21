package com.adsabri.wordvaultbp2.pages;

import com.adsabri.wordvaultbp2.Application;
import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.controllers.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.adsabri.wordvaultbp2.models.Word;

public class ListPage {

    private Stage stage;
    private Pane root;
    private Scene scene;
    private TableView<Word> tableView;
    private VBox topPane;
    private VBox listPane;
    private HBox buttonPane;
    private Label label;
    private Button homeButton;
    private Button addButton;
    private double screenWidth;
    private double screenHeight;

    public ListPage (Stage stage) {

        this.stage = stage;

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
        this.screenWidth = primaryScreenBounds.getWidth();
        this.screenHeight = primaryScreenBounds.getHeight();

        // Maak de root en scene op basis van het schermformaat
        root = new Pane();
        scene = new Scene(root, screenWidth, screenHeight);  // Stel de scene in op full-screen
        scene.getStylesheets().add(Application.class.getResource("stylesheets/listpage.css").toString());

        setupLayout();

        stage.setScene(scene);
        stage.setTitle("List Page");

    }

    private void setupLayout () {

        // Init's
        topPane = new VBox();
        listPane = new VBox();
        buttonPane = new HBox();
        buttonPane.setSpacing(1000);

        label = new Label("Current wordlist");

        homeButton = new Button("Home");
        addButton = new Button("Add");

        // TableView init, voor de woordenlijst
        tableView = new TableView<>();
        tableView.setPrefSize(850, 425);

        // Tabel vullen met data uit de database
        Database db = new Database();
        ShowController sc = new ShowController(db);
        sc.show(tableView);

        // Id's geven aan de onderdelen
        root.setId("rootPane");
        topPane.setId("topPane");
        listPane.setId("listPane");
        buttonPane.setId("buttonPane");
        label.setId("label");
        homeButton.setId("homeButton");
        addButton.setId("addButton");

        // Sizes
        topPane.setPrefSize(300, 100);
        listPane.setPrefSize(850, 425);
        buttonPane.setPrefSize(1400, 50);

        // Plaats panes
        topPane.setLayoutX(620);
        topPane.setLayoutY(40);

        listPane.setLayoutX(340);
        listPane.setLayoutY(200);

        buttonPane.setLayoutX(63);
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

            // AddPage instellen met bijbehorende controllers
            Stage addStage = new Stage();
            Database db = new Database();
            CreateController cc = new CreateController(db);
            UpdateController uc = new UpdateController(db);
            LoginController lc = new LoginController(db);
            CategoryController catc = new CategoryController(db);

            new AddPage(addStage, cc, uc, lc, catc, null);
            addStage.show();

        });

    }

    public Scene getScene() {
        return scene;
    }

    private void addChildren () {
        root.getChildren().addAll(topPane, listPane, buttonPane);
        topPane.getChildren().addAll(label);
        listPane.getChildren().addAll(tableView);
        buttonPane.getChildren().addAll(homeButton, addButton);
    }

}

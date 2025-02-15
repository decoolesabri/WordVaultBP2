package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.models.Word;
import com.adsabri.wordvaultbp2.pages.AddPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowController extends BaseController {

    private UpdateController updateController;
    private DeleteController deleteController;

    public ShowController(Database db) {
        super(db);
        this.updateController = new UpdateController(db);
        this.deleteController = new DeleteController(db);
    }

    public void show(TableView<Word> tableView) {

        // Maak kolommen aan voor TableView
        TableColumn<Word, String> wordColumn = new TableColumn<>("Word");
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

        TableColumn<Word, String> meaningColumn = new TableColumn<>("Meaning");
        meaningColumn.setCellValueFactory(new PropertyValueFactory<>("meaning"));

        TableColumn<Word, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        TableColumn<Word, Void> editColumn = new TableColumn<>("Edit");
        editColumn.setCellFactory(col -> new TableCell<>() {

            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction(e -> {

                    Word selectedWord = getTableView().getItems().get(getIndex());
                    Stage stage = (Stage) getTableView().getScene().getWindow();

                    // Als er een geselecteerd woord is, geef dit door voor bewerken
                    if (selectedWord != null) {
                        AddPage addPage = new AddPage(stage, new CreateController(db), new UpdateController(db), selectedWord);
                        stage.setScene(addPage.getScene());
                    } else {
                        // Als er geen geselecteerd woord is, kun je naar een nieuw woord aanmaken pagina gaan
                        AddPage addPage = new AddPage(stage, new CreateController(db), new UpdateController(db), null);
                        stage.setScene(addPage.getScene());
                    }

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        TableColumn<Word, Void> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setCellFactory(col -> new TableCell<>() {

            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(e -> {

                    Word selectedWord = getTableView().getItems().get(getIndex());
                    if (selectedWord != null) {
                        // Roep de delete-methode aan via de instantie van DeleteController
                        deleteController.deleteWordFromDatabase(selectedWord);
                        getTableView().getItems().remove(selectedWord); // Verwijder het woord uit de TableView
                    }

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        // Voeg kolommen toe aan de TableView
        tableView.getColumns().addAll(wordColumn, meaningColumn, noteColumn, editColumn, deleteColumn);

        // Data ophalen uit de database
        ObservableList<Word> wordsList = FXCollections.observableArrayList();

        String query = "SELECT * FROM word";
        try (PreparedStatement stmt = db.getConn().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                wordsList.add(new Word(
                        rs.getInt("id"),
                        rs.getString("word"),
                        rs.getString("meaning"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Voeg de data toe aan de TableView
        tableView.setItems(wordsList);

    }

}

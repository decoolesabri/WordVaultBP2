package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.UserSession;
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

                    if (selectedWord != null) {
                        AddPage addPage = new AddPage(stage, new CreateController(db), new UpdateController(db), new LoginController(db), new CategoryController(db), selectedWord);
                        stage.setScene(addPage.getScene());
                    } else {
                        AddPage addPage = new AddPage(stage, new CreateController(db), new UpdateController(db), new LoginController(db), new CategoryController(db), null);
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
                        deleteController.deleteWordFromDatabase(selectedWord);
                        getTableView().getItems().remove(selectedWord);
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

        tableView.getColumns().addAll(wordColumn, meaningColumn, noteColumn, editColumn, deleteColumn);

        // Controleer of de gebruiker is ingelogd
        if (!UserSession.isLoggedIn()) {
            System.out.println("Geen gebruiker ingelogd. Kan woorden niet ophalen.");
            return;
        }

        // Data ophalen uit de database
        ObservableList<Word> wordsList = FXCollections.observableArrayList();

        String query = "SELECT w.id, w.word, w.meaning, w.note " +
                "FROM word w " +
                "INNER JOIN user_word uw ON w.id = uw.word_id " +
                "WHERE uw.user_id = ?";
        try (PreparedStatement stmt = db.getConn().prepareStatement(query)) {
            stmt.setInt(1, UserSession.getLoggedInUserId()); // Gebruik het ingelogde user_id
            ResultSet rs = stmt.executeQuery();

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
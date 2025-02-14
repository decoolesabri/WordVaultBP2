package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.models.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowController extends BaseController {

    public ShowController(Database db) {
        super(db);
    }

    public void show(TableView<Word> tableView) {

        // Maak kolommen aan voor TableView
        TableColumn<Word, String> wordColumn = new TableColumn<>("Word");
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

        TableColumn<Word, String> meaningColumn = new TableColumn<>("Meaning");
        meaningColumn.setCellValueFactory(new PropertyValueFactory<>("meaning"));

        TableColumn<Word, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

        // Voeg kolommen toe aan de TableView
        tableView.getColumns().addAll(wordColumn, meaningColumn, noteColumn);

        // Data ophalen uit de database
        ObservableList<Word> wordsList = FXCollections.observableArrayList();

        try {
            ResultSet rs = this.stmt.executeQuery("SELECT * FROM word");

            while (rs.next()) {
                wordsList.add(new Word(
                        rs.getString("word"),
                        rs.getString("meaning"),
                        rs.getString("note")
                ));
            }

            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Voeg de data toe aan de TableView
        tableView.setItems(wordsList);

    }
}

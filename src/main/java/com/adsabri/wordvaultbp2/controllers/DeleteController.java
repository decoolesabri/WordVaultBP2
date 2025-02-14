package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;

import java.sql.SQLException;

public class DeleteController extends BaseController {

    public DeleteController (Database db) {
        super(db);
    }

    public void delete(String word) {
        try {
            this.stmt.execute("DELETE FROM word WHERE word = '" + word + "'");
            System.out.println("Woord succesvol verwijderd: " + word);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;

import java.sql.SQLException;
import java.sql.Statement;

public class CreateController {

    private Database db;
    private Statement stmt;

    public CreateController (Database db) {
        this.db = db;
        try {
            this.stmt = this.db.getConn().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void opslaan (String word, String meaning, String note) {
        try {
            this.stmt.execute("INSERT INTO word (word, meaning, note) VALUES ('" + word + "','" + meaning + "','" + note + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



//    public boolean handleCreate (String word, String meaning, String note) {
//
//        if (!word.isEmpty() && !meaning.isEmpty() && !note.isEmpty()) { // Note mag empty zijn, dit nog maken
//            System.out.println("Spaans woord: " + word);
//            System.out.println("Vertaling: " + meaning);
//            System.out.println("Zin/notitie: " + note);
//            System.out.println("Woord succesvol opgeslagen!");
//            return true; // Login is "succesvol"
//        } else {
//            System.out.println("Opslaan mislukt: velden mogen niet leeg zijn!");
//            return false; // Login mislukt
//        }
//
//    }

}

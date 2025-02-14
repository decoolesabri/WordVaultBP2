//package com.adsabri.wordvaultbp2.controllers;
//
//import com.adsabri.wordvaultbp2.Database;
package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;

import java.sql.SQLException;

public class CreateController extends BaseController {

    public CreateController(Database db) {
        super(db);
    }

    public void save (String word, String meaning, String note) {

        try {
            this.stmt.execute("INSERT INTO word (word, meaning, note) VALUES ('" + word + "','" + meaning + "','" + note + "')");
            System.out.println("Spaans woord: " + word);
            System.out.println("Vertaling: " + meaning);
            System.out.println("Zin/notitie: " + note);
            System.out.println("Woord succesvol opgeslagen!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


//
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class CreateController {
//
//    private Database db;
//    private Statement stmt;
//
//    public CreateController (Database db) {
//        this.db = db;
//        try {
//            this.stmt = this.db.getConn().createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void opslaan (String word, String meaning, String note) {
//        try {
//            this.stmt.execute("INSERT INTO word (word, meaning, note) VALUES ('" + word + "','" + meaning + "','" + note + "')");
//            System.out.println("Spaans woord: " + word);
//            System.out.println("Vertaling: " + meaning);
//            System.out.println("Zin/notitie: " + note);
//            System.out.println("Woord succesvol opgeslagen!");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}

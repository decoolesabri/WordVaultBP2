package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.models.Word;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateController extends BaseController {

    public UpdateController(Database db) {
        super(db);
    }

    public void updateWord(Word word, int categoryId) {

        try {
            // Woord bijwerken in de database
            String sql = "UPDATE word SET word = ?, meaning = ?, note = ? WHERE id = ?";
            try (PreparedStatement stmt = db.getConn().prepareStatement(sql)) {
                stmt.setString(1, word.getWord());
                stmt.setString(2, word.getMeaning());
                stmt.setString(3, word.getNote());
                stmt.setInt(4, word.getId());
                stmt.executeUpdate();
            }

            // Categorie bijwerken in database
            sql = "UPDATE word_category SET category_id = ? WHERE word_id = ?";
            try (PreparedStatement stmt = db.getConn().prepareStatement(sql)) {
                stmt.setInt(1, categoryId);  // Nieuwe category_id
                stmt.setInt(2, word.getId()); // Het woord_id
                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

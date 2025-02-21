package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.models.Word;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteController extends BaseController {

    public DeleteController(Database db) {
        super(db);
    }

    // Verwijderen van een woord
    public void deleteWordFromDatabase(Word word) {

        String sql = "DELETE FROM word WHERE id = ?";
        try (PreparedStatement stmt = db.getConn().prepareStatement(sql)) {
            stmt.setInt(1, word.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

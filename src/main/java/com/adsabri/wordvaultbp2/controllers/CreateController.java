package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.UserSession; // Zorg ervoor dat je de UserSession klasse importeert

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateController extends BaseController {

    public CreateController(Database db) {
        super(db);
    }

    public boolean userExists(int userId) {
        try {
            String query = "SELECT id FROM user WHERE id = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(query);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retourneert true als de gebruiker bestaat
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void save(String word, String meaning, String note) {
        // Haal het loggedInUserId op uit de UserSession
        int loggedInUserId = UserSession.getLoggedInUserId();

        // Controleer of de gebruiker bestaat, anders stop de operatie.
        if (!userExists(loggedInUserId)) {
            System.out.println("De gebruiker bestaat niet, kan geen woorden opslaan.");
            return;
        }

        try {
            // 1. Voeg het woord toe aan de word tabel
            String insertWordQuery = "INSERT INTO word (word, meaning, note) VALUES (?, ?, ?)";
            PreparedStatement insertWordStmt = db.getConn().prepareStatement(insertWordQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            insertWordStmt.setString(1, word);
            insertWordStmt.setString(2, meaning);
            insertWordStmt.setString(3, note);

            int rowsAffected = insertWordStmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = insertWordStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newWordId = generatedKeys.getInt(1); // Verkrijg de gegenereerde word_id
                    System.out.println("Spaans woord opgeslagen met ID: " + newWordId);

                    // 2. Voeg een record toe in de user_word tabel om het woord te koppelen aan de gebruiker
                    String insertUserWordQuery = "INSERT INTO user_word (user_id, word_id) VALUES (?, ?)";
                    PreparedStatement insertUserWordStmt = db.getConn().prepareStatement(insertUserWordQuery);
                    insertUserWordStmt.setInt(1, loggedInUserId);
                    insertUserWordStmt.setInt(2, newWordId);

                    insertUserWordStmt.executeUpdate();
                    System.out.println("Woord succesvol gekoppeld aan gebruiker ID: " + loggedInUserId);
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL-fout: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Fout bij het opslaan van het woord of koppelen aan gebruiker.", e);
        }
    }
}

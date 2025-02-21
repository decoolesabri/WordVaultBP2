package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.UserSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateController extends BaseController {

    public CreateController(Database db) {
        super(db);
    }

    // Bestaat de user?
    public boolean userExists(int userId) {
        try {
            String query = "SELECT id FROM user WHERE id = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(query);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Opslaan van een woord met bijbehorende data
    public int save (String word, String meaning, String note, String categoryName) {
        // Haal het loggedInUserId op uit de UserSession
        int loggedInUserId = UserSession.getLoggedInUserId();

        // Controleer of de gebruiker bestaat, anders stop de operatie.
        if (!userExists(loggedInUserId)) {
            System.out.println("De gebruiker bestaat niet, kan geen woorden opslaan.");
            return loggedInUserId;
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

                    // 3. Zoek de category_id op basis van de naam
                    int categoryId = getCategoryId(categoryName);
                    if (categoryId != -1) {
                        // 4. Voeg de categorie-koppeling toe aan word_category
                        String insertWordCategoryQuery = "INSERT INTO word_category (word_id, category_id) VALUES (?, ?)";
                        PreparedStatement insertWordCategoryStmt = db.getConn().prepareStatement(insertWordCategoryQuery);
                        insertWordCategoryStmt.setInt(1, newWordId);
                        insertWordCategoryStmt.setInt(2, categoryId);
                        insertWordCategoryStmt.executeUpdate();
                        System.out.println("Categorie succesvol gekoppeld aan woord ID: " + newWordId);
                    } else {
                        System.out.println("Fout: categorie niet gevonden.");
                    }

                }
            }

        } catch (SQLException e) {
            System.err.println("SQL-fout: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Fout bij het opslaan van het woord of koppelen aan gebruiker.", e);
        }
        return loggedInUserId;
    }

    // Category id zoeken
    private int getCategoryId(String categoryName) {
        try {
            String query = "SELECT id FROM category WHERE name = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(query);
            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Geeft -1 terug als de categorie niet bestaat
    }

}

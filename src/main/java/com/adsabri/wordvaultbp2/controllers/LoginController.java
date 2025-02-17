package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import com.adsabri.wordvaultbp2.UserSession;
import com.adsabri.wordvaultbp2.controllers.BaseController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends BaseController {
    private int loggedInUserId;

    public LoginController(Database db) {
        super(db);
    }

    public int getLoggedInUserId() {
        return loggedInUserId;
    }

    public boolean handleLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Login mislukt: velden mogen niet leeg zijn!");
            return false;
        }

        try {
            String checkQuery = "SELECT id FROM user WHERE name = ? AND password = ?";
            PreparedStatement checkStmt = db.getConn().prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            checkStmt.setString(2, password);

            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                loggedInUserId = rs.getInt("id");
                UserSession.setLoggedInUserId(loggedInUserId);  // Sla het userId op in UserSession
                System.out.println("Gebruiker bestaat al. ID: " + loggedInUserId);
                return true;
            } else {
                String insertQuery = "INSERT INTO user (name, password) VALUES (?, ?)";
                PreparedStatement insertStmt = db.getConn().prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);

                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        loggedInUserId = generatedKeys.getInt(1);
                        UserSession.setLoggedInUserId(loggedInUserId);  // Sla het nieuwe userId op in UserSession
                        System.out.println("Nieuwe gebruiker aangemaakt met ID: " + loggedInUserId);
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}

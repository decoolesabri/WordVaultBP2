package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CreateControllerTest {

    private CreateController createController;
    private Database db;

    @BeforeEach
    void setUp() throws SQLException {
        db = new Database();
        createController = new CreateController(db);

        // Voeg testgebruiker toe als hij niet bestaat
        String query = "INSERT INTO user (id, name, password) VALUES (1, 'testuser', 'testpass') "
                + "ON DUPLICATE KEY UPDATE name=name;";
        PreparedStatement stmt = db.getConn().prepareStatement(query);
        stmt.executeUpdate();
    }


    @Test
    void userExists() {
        // Test een bestaand userId (stel voor dat 1 een bestaande gebruiker is)
        boolean result = createController.userExists(1);
        assertTrue(result, "De gebruiker zou moeten bestaan.");

        // Test een niet-bestaand userId (stel voor dat 9999 geen gebruiker is)
        result = createController.userExists(9999);
        assertFalse(result, "De gebruiker zou niet moeten bestaan.");
    }


}
package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CreateControllerTest {

    private CreateController createController;
    private Database db;

    @BeforeEach
    void setUp() {
        // Zorg ervoor dat je een tijdelijke database of mockverbinding hebt
        db = new Database(); // Simuleer of verbind met de database
        createController = new CreateController(db);
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

    @Test
    void save() {
        String word = "Hola";
        String meaning = "Hello";
        String note = "Used as a greeting";
        String categoryName = "Greetings";

        // Stel een bestaand logged-in userId in de sessie in
        int loggedInUserId = 1; // Dit zou moeten overeenkomen met een bestaande gebruiker in je database

        // Test het opslaan van een woord
        int result = createController.save(word, meaning, note, categoryName);

        // Controleer of het juiste userId wordt geretourneerd
        assertEquals(loggedInUserId, result, "Het opgeslagen woord zou gekoppeld moeten worden aan de ingelogde gebruiker.");

        // Controleer of het woord is opgeslagen in de database
        assertTrue(wordIsStoredInDatabase(word), "Het woord zou moeten worden opgeslagen in de database.");

    }

    private boolean wordIsStoredInDatabase(String word) {
        try {
            String query = "SELECT id FROM word WHERE word = ?";
            PreparedStatement stmt = db.getConn().prepareStatement(query);
            stmt.setString(1, word);
            ResultSet rs = stmt.executeQuery();

            // Als er een record is, dan is het woord opgeslagen
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


//package com.adsabri.wordvaultbp2.controllers;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CreateControllerTest {
//
//    @Test
//    void userExists() {
//    }
//
//    @Test
//    void save() {
//    }
//}
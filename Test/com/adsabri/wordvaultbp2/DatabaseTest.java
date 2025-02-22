package com.adsabri.wordvaultbp2;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void getConn() {
        Database db = new Database();

        Connection conn = db.getConn();

        // Controleer of de verbinding niet null is
        assertNotNull(conn, "De databaseverbinding mag niet null zijn.");

        // Controleer of de verbinding daadwerkelijk open is
        try {
            assertTrue(conn.isValid(2), "De verbinding moet geldig zijn.");
        } catch (SQLException e) {
            fail("Er is een fout opgetreden bij het controleren van de verbinding: " + e.getMessage());
        }
    }
}

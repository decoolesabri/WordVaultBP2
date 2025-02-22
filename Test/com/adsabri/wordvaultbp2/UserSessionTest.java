package com.adsabri.wordvaultbp2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @BeforeEach
    void setUp() {
        UserSession.setLoggedInUserId(-1);
    }

    @Test
    void testIsLoggedInWhenNotLoggedIn() {
        // Controleer of isLoggedIn false is als er geen gebruiker is ingelogd
        assertFalse(UserSession.isLoggedIn(), "Er zou geen gebruiker ingelogd moeten zijn.");
    }

    @Test
    void testIsLoggedInWhenLoggedIn() {
        // Zet een gebruiker in de sessie
        UserSession.setLoggedInUserId(1);

        // Controleer of isLoggedIn true is wanneer een gebruiker is ingelogd
        assertTrue(UserSession.isLoggedIn(), "Er zou een gebruiker ingelogd moeten zijn.");
    }
}

package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private Database db;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        db = new Database();
        loginController = new LoginController(db);
    }

    @Test
    void handleLoginWithEmptyFields() {
        // Test met lege velden
        boolean result = loginController.handleLogin("", "");
        assertFalse(result, "De login zou moeten falen bij lege velden.");
    }
}

package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    private CategoryController categoryController;
    private Database db;

    @BeforeEach
    void setUp() {
        db = new Database();
        categoryController = new CategoryController(db);
    }

    @Test
    void getCategoryId() throws SQLException {
        int expectedCategoryId = 12;

        // Test of getCategoryId de juiste ID teruggeeft voor de categorie "noun"
        Integer resultCategoryId = categoryController.getCategoryId("noun");
        assertEquals(expectedCategoryId, resultCategoryId, "De category id voor 'noun' zou 12 moeten zijn.");

        // Test voor een niet-bestaande categorie
        resultCategoryId = categoryController.getCategoryId("NonExistentCategory");
        assertNull(resultCategoryId, "De category id voor een niet-bestaande categorie zou null moeten zijn.");
    }
}

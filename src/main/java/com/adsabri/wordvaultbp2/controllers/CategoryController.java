package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;

public class CategoryController extends BaseController {

    public CategoryController(Database db) {
        super(db);
    }

    public void handleCategorySelection(String selectedCategory) {
        // Verwerk de geselecteerde categorie
        System.out.println("Geselecteerde categorie: " + selectedCategory);
    }

    public void saveCategory(String category) {
        // Sla de nieuwe categorie op in de database of een andere opslag
        System.out.println("Categorie opgeslagen: " + category);
    }
}


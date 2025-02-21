package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryController extends BaseController {

    // Id's bijhouden
    private Map<String, Integer> categoryMap = new HashMap<>();

    public CategoryController(Database db) {
        super(db);
        loadCategories();
    }

    // Haal categorieën uit de database en sla ze op in een map
    private void loadCategories() {
        try {
            String query = "SELECT id, name FROM category";
            PreparedStatement stmt = db.getConn().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                categoryMap.put(rs.getString("name"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Geeft de id van een categorie terug
    public Integer getCategoryId(String categoryName) {
        return categoryMap.get(categoryName);
    }

    public List<String> getCategories() {
        return new ArrayList<>(categoryMap.keySet());
    }

}

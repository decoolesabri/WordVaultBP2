package com.adsabri.wordvaultbp2.controllers;

import com.adsabri.wordvaultbp2.Database;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseController {

    protected Database db;
    protected Statement stmt;

    public BaseController (Database db) {

        this.db = db;
        try {
            this.stmt = this.db.getConn().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

package com.adsabri.wordvaultbp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection conn;

    public Connection getConn() {
        return conn;
    }

    public Database() {

        //Verbinding met de database
        try {
            DriverManager.getConnection("jdbc:mysql://localhost/wordvaultspanish", "root", "");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

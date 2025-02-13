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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/wordvaultspanish", "root", "");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Kan geen verbinding maken met de database");
//            throw new RuntimeException(e);
        }

    }

}

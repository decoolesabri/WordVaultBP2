package com.adsabri.wordvaultbp2;

import com.adsabri.wordvaultbp2.controllers.CreateController;
import com.adsabri.wordvaultbp2.controllers.LoginController;
import com.adsabri.wordvaultbp2.pages.AddPage;
import com.adsabri.wordvaultbp2.pages.HomePage;
import com.adsabri.wordvaultbp2.pages.ListPage;
import com.adsabri.wordvaultbp2.pages.LoginPage;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start (Stage stage) throws IOException {

        Database db = new Database();
        CreateController cc = new CreateController(db);
        LoginController lc = new LoginController(db);

        LoginPage loginPage = new LoginPage(stage, lc);

        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

// test van controllers en CRUD maken

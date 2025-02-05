package com.adsabri.wordvaultbp2;

import com.adsabri.wordvaultbp2.pages.AddPage;
import com.adsabri.wordvaultbp2.pages.HomePage;
import com.adsabri.wordvaultbp2.pages.ListPage;
import com.adsabri.wordvaultbp2.pages.LoginPage;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

        LoginPage loginPage = new LoginPage(stage);
//        HomePage homePage = new HomePage(stage);
//        AddPage addPage = new AddPage(stage);
//        ListPage listPage = new ListPage(stage);

        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

// Stylesheet advies verwerken
// Vragen of anderen 1 hardcoded user hebben of meerdere kunnen opslaan in database
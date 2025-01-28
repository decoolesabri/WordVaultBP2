package com.adsabri.wordvaultbp2;

import com.adsabri.wordvaultbp2.pages.HomePage;
import com.adsabri.wordvaultbp2.pages.LoginPage;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {

//        LoginPage loginPage = new LoginPage(stage);
        HomePage homePage = new HomePage(stage);

        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

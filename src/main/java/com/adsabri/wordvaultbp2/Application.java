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
    public void start(Stage stage) throws IOException {

        Database db = new Database();
        CreateController cc = new CreateController(db);
        LoginController lc = new LoginController(db);

        LoginPage loginPage = new LoginPage(stage, lc);
//        HomePage homePage = new HomePage(stage);
//        AddPage addPage = new AddPage(stage, cc);
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
// Namen van de onderdelen goed maken, engels/nederlands
// Comments toevoegen
// test van controllers maken

// Succesmelding woord create en update

// Woord word nu gekoppeld met user id, maar elke user kan de woorden zien,
// zorg ervoor dat de view van woorden ook aan user id gekoppeld word
// (zo ook bewerken en verwijderen, maar dat zit er automatisch bij)
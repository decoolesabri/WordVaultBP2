module com.adsabri.wordvaultbp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adsabri.wordvaultbp2 to javafx.fxml;
    exports com.adsabri.wordvaultbp2;
}
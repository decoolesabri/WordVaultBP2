module com.adsabri.wordvaultbp2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.adsabri.wordvaultbp2 to javafx.fxml;
    exports com.adsabri.wordvaultbp2;
}
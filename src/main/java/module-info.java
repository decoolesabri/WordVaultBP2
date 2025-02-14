module com.adsabri.wordvaultbp2 {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.adsabri.wordvaultbp2.models to javafx.base;
    exports com.adsabri.wordvaultbp2;
}


//module com.adsabri.wordvaultbp2 {
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires java.desktop;
//    requires java.sql;
//
//
//    opens com.adsabri.wordvaultbp2 to javafx.fxml;
//    exports com.adsabri.wordvaultbp2;
//}
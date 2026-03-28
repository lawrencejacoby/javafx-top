module de.wahl.javafx.top {
    requires javafx.controls;
    // falls du FXML nutzt, hier einkommentieren:
    // requires javafx.fxml; 

    // Das hier erlaubt JavaFX den Zugriff auf deine Main-Klasse
    exports de.wahl.javafx.top;
}
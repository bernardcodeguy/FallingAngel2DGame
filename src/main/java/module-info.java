module games.fx.fallingangel2dgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens games.fx to javafx.fxml;
    exports games.fx;
}
package games.fx;

import javafx.scene.image.ImageView;

public class Enemy extends ImageView {

    public Enemy() {
        super();

        setLayoutX(0); // set the initial X position to 0
        setLayoutY(0); // set the initial Y position to 0
    }

    public void setPosition(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }
}

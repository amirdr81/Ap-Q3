package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import transition.ballAnimation;

public class ball {
    private int health = 3;
    private ImageView imageView = new ImageView();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ImageView getImageView() {
        return imageView;
    }
}

package Model;

import javafx.scene.image.ImageView;

public class ball {
    private int health = 3;
    private final ImageView imageView = new ImageView();

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

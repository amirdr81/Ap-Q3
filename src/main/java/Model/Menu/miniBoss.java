package Model.Menu;

import javafx.scene.image.ImageView;

public class miniBoss {
    private int health = 3;
    private final ImageView imageView;

    public miniBoss(ImageView imageView){
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

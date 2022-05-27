package Model;

import javafx.scene.image.ImageView;

public class plane {
    private ImageView imageView;
    private int health = 20;
    private boolean isBlink = false;
    private int bombFull = 0;

    public plane(ImageView imageView)
    {
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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public boolean isBlink() {
        return isBlink;
    }

    public void setBlink() {
        isBlink = !isBlink;
    }

    public void setBombFull(int bombFull) {
        this.bombFull = bombFull;
    }

    public int getBombFull() {
        return bombFull;
    }
}

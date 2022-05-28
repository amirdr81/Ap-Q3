package Model;

import javafx.geometry.Bounds;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import transition.ballAnimation;

import java.awt.*;
import java.awt.image.BufferedImage;

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

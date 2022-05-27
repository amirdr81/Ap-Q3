package transition;

import javafx.animation.Transition;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class bulletExplosionAnimation extends Transition {
    private final ImageView imageView;
    private final Image[] images = new Image[4];
    private int imageCounter = 0;

    public bulletExplosionAnimation(ImageView imageView, ImageView plane)
    {
        this.imageView = imageView;
        this.imageView.setX(plane.getX() + 100);
        this.imageView.setY(plane.getY() + 20);
        for(int i = 0; i < 4; i++)
            images[i] = new Image("photos/bullet/explosion/" + (i + 1) + ".png");
        setCycleDuration(Duration.millis(2000));
        setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        if (imageCounter == 3)
            imageView.setImage(null);
        else {
            imageView.setImage(images[imageCounter]);
            imageCounter++;
        }
    }
}

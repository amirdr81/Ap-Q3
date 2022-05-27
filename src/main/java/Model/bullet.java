package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bullet {
    private final ImageView imageView = new ImageView();

    public bullet(double x, double y)
    {
        this.imageView.setImage(new Image("photos/bullet/bullet.png"));
        this.imageView.setX(x);
        this.imageView.setY(y);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Bounds getLayoutBounds() {
        return imageView.getLayoutBounds();
    }
}

package Model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class bomb {
    private final ImageView imageView = new ImageView();

    public bomb(double x, double y)
    {
        this.imageView.setImage(new Image("photos/bullet/redBomb.png"));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
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

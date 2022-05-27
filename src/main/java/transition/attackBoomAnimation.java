package transition;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class attackBoomAnimation extends Transition {
    private final ImageView img;
    private final Image[] frames = new Image[8];
    private int frameCounter = 0;

    public attackBoomAnimation(ImageView img, double x, double y, double width, double height)
    {
        this.img = img;
        for(int i = 0; i < 8; i++)
            frames[i] = new Image("photos/bullet/attackExplosion/" + (i + 1) + ".png");
        img.setFitWidth(width);
        img.setFitHeight(height);
        img.setX(img.getX() + x);
        img.setY(img.getY() + y);
        setCycleDuration(Duration.millis(1400));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        img.setImage(frames[frameCounter]);
        frameCounter++;
        if(frameCounter == 8) {
            img.setImage(null);
            pause();
        }
    }

}

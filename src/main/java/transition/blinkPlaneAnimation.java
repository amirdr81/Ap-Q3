package transition;

import Model.plane;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class blinkPlaneAnimation extends Transition {
    private final Model.plane plane;
    private int frameCounter = 0;
    private final Image image;

    public blinkPlaneAnimation(plane plane)
    {
        this.plane = plane;
        image = plane.getImageView().getImage();
        setCycleDuration(Duration.seconds(7));
        setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        frameCounter++;
        if(frameCounter % 10 == 0) {
            if (plane.getImageView().getImage() == image)
                plane.getImageView().setImage(null);
            else
                plane.getImageView().setImage(image);
        }
        if(frameCounter == 360) {
            plane.setBlink();
            pause();
        }
    }
}

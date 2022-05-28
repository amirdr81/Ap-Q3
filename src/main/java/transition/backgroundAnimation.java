package transition;

import javafx.animation.Transition;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class backgroundAnimation extends Transition {
    private final ImageView background;
    private final double speed;

    public backgroundAnimation(ImageView background, double speed) {
        this.background = background;
        this.speed = speed;
        this.setCycleDuration(Duration.millis(50));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        background.setX(background.getX() - speed);
        if(background.getX() <= -1 * background.getImage().getWidth())
            background.setX(1280);
    }

    public ImageView getBackground() {
        return background;
    }
}

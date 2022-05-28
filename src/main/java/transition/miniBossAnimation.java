package transition;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class miniBossAnimation extends Transition {
    private final ImageView[] miniBosses;
    private final Image[] frames = new Image[12];
    private int frameCounter = 0;

    public miniBossAnimation(ImageView[] miniBosses)
    {
        this.miniBosses = miniBosses;
        setFrames(0,3, "photos/miniBoss/purple/1.png");
        setFrames(3,6, "photos/miniBoss/purple/2.png");
        setFrames(6,9, "photos/miniBoss/purple/3.png");
        setFrames(9,12, "photos/miniBoss/purple/4.png");
        for(int i = 0; i < 4; i++) {
            miniBosses[i].setFitWidth(100);
            miniBosses[i].setFitHeight(75);
        }
        setCycleDuration(Duration.millis(1000));
        setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        for(int i = 0; i < 4; i++) {
            if(miniBosses[i].getImage() != null)
                miniBosses[i].setImage(frames[frameCounter % 12]);
            miniBosses[i].setX(miniBosses[i].getX() - 3);
        }
        frameCounter++;
    }
    private void setFrames(int a, int b, String url)
    {
        for(int i = a; i < b; i++)
            frames[i] = new Image(url);
    }

    public String getFrame1() {
        return "photos/miniBoss/purple/1.png";
    }
}

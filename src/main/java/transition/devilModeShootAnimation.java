package transition;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class devilModeShootAnimation extends Transition {
    private final ImageView boss;
    private final Image[] frames = new Image[40];
    private int frameCounter = 0;

    public devilModeShootAnimation(ImageView boss)
    {
        this.boss = boss;
        setFrames(0,5, "photos/bossP2/shoot/1.png");
        setFrames(5,10, "photos/bossP2/shoot/2.png");
        setFrames(10,15, "photos/bossP2/shoot/3.png");
        setFrames(15,20, "photos/bossP2/shoot/4.png");
        setFrames(20,25, "photos/bossP2/shoot/5.png");
        setFrames(25,30, "photos/bossP2/shoot/3.png");
        setFrames(30,35, "photos/bossP2/shoot/2.png");
        setFrames(35,40, "photos/bossP2/shoot/1.png");
        setCycleDuration(Duration.millis(666));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        boss.setImage(frames[frameCounter % 40]);
        frameCounter++;
    }
    private void setFrames(int a, int b, String url)
    {
        for(int i = a; i < b; i++)
            frames[i] = new Image(url);
    }
}

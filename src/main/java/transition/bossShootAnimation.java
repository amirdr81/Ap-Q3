package transition;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import javafx.scene.image.ImageView;

public class bossShootAnimation extends Transition {
    private final ImageView boss;
    private final Image[] images = new Image[72];
    private int frameCounter = 0;


    public bossShootAnimation(ImageView boss)
    {
        this.boss = boss;
        setImageFrames(0, 3, "photos/bossShoot/1.png");
        setImageFrames(3, 6, "photos/bossShoot/2.png");
        setImageFrames(6, 9, "photos/bossShoot/3.png");
        setImageFrames(9, 12, "photos/bossShoot/4.png");
        setImageFrames(12, 15, "photos/bossShoot/5.png");
        setImageFrames(15, 18, "photos/bossShoot/6.png");
        setImageFrames(18, 21, "photos/bossShoot/7.png");
        setImageFrames(21, 24, "photos/bossShoot/8.png");
        setImageFrames(24, 27, "photos/bossShoot/9.png");
        setImageFrames(27, 30, "photos/bossShoot/10.png");
        setImageFrames(30, 33, "photos/bossShoot/11.png");
        setImageFrames(33, 36, "photos/bossShoot/12.png");
        setCycleDuration(Duration.seconds(0.7));
        setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        boss.setImage(images[frameCounter % 72]);
        frameCounter++;
        if(frameCounter == 1)
            boss.setX(boss.getX() - 150);
        if(frameCounter == 72)
            pause();
    }
    private void setImageFrames(int a, int b, String url)
    {
        for(int i = a; i < b; i++) {
            images[i] = new Image(url);
            images[71 - i] = images[i];
        }
    }
    public void setFrameCounter()
    {
        this.frameCounter = 0;
    }
}

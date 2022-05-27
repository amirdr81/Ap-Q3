package transition;

import Model.boss;
import javafx.animation.Timeline;
import javafx.animation.Transition;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class devilModeP2Animation extends Transition {
    private final Model.boss boss;
    private final Image[] frames = new Image[40];
    private int frameCounter = 0;
    private int rangeSpeedX = 3;
    private int rangeSpeedY = 3;
    private final devilModeShootAnimation devilModeShootAnimation;
    public devilModeP2Animation(boss boss)
    {
        this.boss = boss;
        devilModeShootAnimation = new devilModeShootAnimation(boss.getImageView());
        setFrames(0,5, "photos/bossP2/1.png");
        setFrames(5,10, "photos/bossP2/2.png");
        setFrames(10,15, "photos/bossP2/3.png");
        setFrames(15,20, "photos/bossP2/4.png");
        setFrames(20,25, "photos/bossP2/5.png");
        setFrames(25,30, "photos/bossP2/4.png");
        setFrames(30,35, "photos/bossP2/3.png");
        setFrames(35,40, "photos/bossP2/2.png");
        this.boss.changeSizeOfBall();
        setCycleDuration(Duration.millis(2000));
        setCycleCount(Timeline.INDEFINITE);
    }

    @Override
    protected void interpolate(double v) {
        boss.getImageView().setImage(frames[frameCounter % 40]);
        frameCounter++;
        ImageView tmp = boss.getImageView();
        tmp.setX(tmp.getX() + rangeSpeedX);
        tmp.setY(tmp.getY() + rangeSpeedY);
        if(tmp.getX() <= 0 || tmp.getX() >= 1020)
            rangeSpeedX *= -1;
        if(tmp.getY() <= 0 || tmp.getY() >= 550)
            rangeSpeedY *= -1;
        if((frameCounter + 1) % 400 == 0) {
            devilModeShootAnimation.play();
            boss.rePositionBallDevilMode();
            boss.reDirectBall();
            boss.ballAnimation();
        }
    }
    private void setFrames(int a, int b, String url)
    {
        for(int i = a; i < b; i++)
            frames[i] = new Image(url);
    }
}

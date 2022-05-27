package transition;

import Model.ball;
import Model.boss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ballAnimation extends Transition {
    private final ball ball;
    private final Image[] frames = new Image[14];
    private int frameCounter= 0;

    public ballAnimation(boss boss)
    {
        ball = boss.getBall();
        ball.getImageView().setFitWidth(100);
        ball.getImageView().setFitHeight(100);
        ball.getImageView().setX(boss.getImageView().getX() + 100);
        ball.getImageView().setY(boss.getImageView().getY() + 150);
        setFrames(0,2, "photos/ball/1.png");
        setFrames(2,4, "photos/ball/2.png");
        setFrames(4,6, "photos/ball/3.png");
        setFrames(6,8, "photos/ball/4.png");
        setFrames(8,10, "photos/ball/5.png");
        setFrames(10,12, "photos/ball/6.png");
        setFrames(12,14, "photos/ball/7.png");
        setCycleDuration(Duration.millis(1000));
        setCycleCount(-1);
    }
    @Override
    protected void interpolate(double v) {
        ball.getImageView().setImage(frames[frameCounter % 14]);
        frameCounter++;
        if(ball.getImageView().getX() >= 500)
            ball.getImageView().setX(ball.getImageView().getX() - 8);
        else
            ball.getImageView().setX(ball.getImageView().getX() - 5.4);
        if(ball.getImageView().getX() <= -180)
            pause();
    }
    private void setFrames(int a, int b, String url)
    {
        for(int i = a; i < b; i++) {
            frames[i] = new Image(url);
        }
    }
}

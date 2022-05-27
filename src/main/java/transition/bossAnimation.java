package transition;

import Model.boss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class bossAnimation extends Transition {

    private final boss boss;
    private final Image[] images = new Image[30];
    private int speed = 6;
    private int tmp = 0;
    private int imageCounter = 0;
    private final bossShootAnimation bossShootAnimation;

    public bossAnimation(boss boss) {
        this.boss = boss;
        bossShootAnimation = new bossShootAnimation(boss.getImageView());
        String frame1 = "photos/bossFly/1.png";
        for (int i = 0; i < 3; i++)
            images[i] = new Image(frame1);
        String frame2 = "photos/bossFly/2.png";
        for (int i = 3; i < 6; i++)
            images[i] = new Image(frame2);
        String frame3 = "photos/bossFly/3.png";
        for (int i = 6; i < 9; i++)
            images[i] = new Image(frame3);
        String frame4 = "photos/bossFly/4.png";
        for (int i = 9; i < 12; i++)
            images[i] = new Image(frame4);
        String frame5 = "photos/bossFly/5.png";
        for (int i = 12; i < 15; i++)
            images[i] = new Image(frame5);
        String frame6 = "photos/bossFly/6.png";
        for (int i = 15; i < 18; i++)
            images[i] = new Image(frame6);
        for (int i = 18; i < 21; i++)
            images[i] = images[15];
        for (int i = 21; i < 24; i++)
            images[i] = images[9];
        for (int i = 24; i < 27; i++)
            images[i] = images[6];
        for (int i = 27; i < 30; i++)
            images[i] = images[3];
        this.setCycleDuration(Duration.millis(10000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        boss.getImageView().setImage(images[imageCounter % 30]);
        boss.getImageView().setY(boss.getImageView().getY() - speed);
        if(boss.getImageView().getY() <= -100 || boss.getImageView().getY() >= 300)
            speed *= -1;
        if((imageCounter + 1) % 400 == 0) {
            tmp = imageCounter;
            bossShootAnimation.setFrameCounter();
            bossShootAnimation.play();
            boss.rePositionBall();
            boss.reDirectBall();
            boss.ballAnimation();
        }
        imageCounter++;
        if(imageCounter > 80 && imageCounter == tmp + 44)
            boss.getImageView().setX(boss.getImageView().getX() + 150);
    }
}

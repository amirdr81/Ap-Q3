package Model;

import javafx.scene.image.ImageView;
import transition.ballAnimation;

public class boss {
    private final ImageView imageView;
    private int health = 5000;
    private final ball ball = new ball();
    private final ballAnimation ballAnimation;

    public boss(ImageView imageView)
    {
        this.imageView = imageView;
        ballAnimation = new ballAnimation(this);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void ballAnimation()
    {
        ballAnimation.play();
    }
    public void stopBallAnimation()
    {
        ballAnimation.pause();
    }

    public ball getBall() {
        return ball;
    }

    public void changeSizeOfBall()
    {
        ball.getImageView().setFitHeight(25);
        ball.getImageView().setFitWidth(25);
    }

    public void rePositionBall()
    {
        ball.getImageView().setX(imageView.getX() + 100);
        ball.getImageView().setY(imageView.getY() + 150);
    }
    public void rePositionBallDevilMode()
    {
        ball.getImageView().setX(imageView.getX() - 15);
        ball.getImageView().setY(imageView.getY() - 25);
    }

    public void reDirectBall() {
        rePositionBall();
        getBall().setHealth(3);
    }
}

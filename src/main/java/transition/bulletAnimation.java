package transition;

import Model.Menu.miniBoss;
import Model.boss;
import Model.bullet;
import Model.plane;
import javafx.animation.Transition;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class bulletAnimation extends Transition {
    private final bullet bullet;
    private final boss boss;
    private final plane plane;
    private final Rectangle health;
    private final Text numberOfHealth;
    private final Rectangle bombFull;
    private final Text text;
    private final miniBoss[] miniBosses;

    public bulletAnimation(bullet bullet, boss boss, Rectangle health, Rectangle bombFull, Text numberOfHealth, plane plane, Text text, miniBoss[] miniBosses)
    {
        this.bullet = bullet;
        this.boss = boss;
        this.health = health;
        this.plane = plane;
        this.bombFull = bombFull;
        this.text = text;
        this.miniBosses = miniBosses;
        this.numberOfHealth = numberOfHealth;
        setCycleDuration(Duration.millis(1000));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        bullet.getImageView().setX(bullet.getImageView().getX() + 21);
        if(inContactWithBoss()) {
            bullet.getImageView().setImage(null);
            boss.setHealth(boss.getHealth() - 9);
            plane.setBombFull(plane.getBombFull() + 1);
            if(bombFull.getWidth() < 101) {
                bombFull.setWidth(bombFull.getWidth() + 1);
            }
            if(bombFull.getWidth() == 101) {
                text.setText("bomb is ready :)");
            }
            health.setWidth(health.getWidth() - 0.45);
            numberOfHealth.setText(String.valueOf((Integer.parseInt(numberOfHealth.getText()) - 9)));
            attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(bullet.getImageView(), 60, -10, 45, 45);
            attackBoomAnimation.play();
            pause();
        }
        if(inContactWithBall()) {
            bullet.getImageView().setImage(null);
            boss.getBall().setHealth(boss.getBall().getHealth() - 1);
            if(boss.getBall().getHealth() == 0) {
                plane.setScore(plane.getScore() + 1);
                boss.stopBallAnimation();
                boss.getBall().getImageView().setImage(null);
                boss.rePositionBall();
                boss.getBall().setHealth(3);
            }
            attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(bullet.getImageView(), 60, -10, 45, 45);
            attackBoomAnimation.play();
            pause();
        }
        if(inContactWithMiniBoss(miniBosses) != null) {
            miniBoss tmp = inContactWithMiniBoss(miniBosses);
            bullet.getImageView().setImage(null);
            assert tmp != null;
            tmp.setHealth(tmp.getHealth() - 1);
            if(tmp.getHealth() == 0) {
                plane.setScore(plane.getScore() + 1);
                tmp.getImageView().setX(1280 + indexOfMiniBoss(tmp) * 75);
                tmp.getImageView().setImage(null);
                tmp.setHealth(3);
            }
            attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(bullet.getImageView(), 60, -10, 45, 45);
            attackBoomAnimation.play();
            pause();
        }
    }
    private int indexOfMiniBoss(miniBoss miniBoss)
    {
        for(int i = 0; i < 4; i++)
            if(miniBosses[i] == miniBoss)
                return i;
        return 0;
    }
    private boolean inContactWithBoss()
    {
        return bullet.getLayoutBounds().intersects(boss.getImageView().getLayoutBounds());
    }
    private boolean inContactWithBall()
    {
        return bullet.getLayoutBounds().intersects(boss.getBall().getImageView().getLayoutBounds());
    }
    private miniBoss inContactWithMiniBoss(miniBoss[] miniBosses)
    {
        for(int i = 0; i < 4; i++)
            if (bullet.getImageView().getBoundsInParent().intersects(miniBosses[i].getImageView().getLayoutBounds()))
                return miniBosses[i];
        return null;
    }
}

package transition;

import Model.Menu.miniBoss;
import Model.bomb;
import Model.boss;
import Model.plane;
import javafx.animation.Transition;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javafx.scene.shape.Rectangle;

public class bombAnimation extends Transition {
    private final bomb bomb;
    private final boss boss;
    private final plane plane;
    private final Rectangle health;
    private final Text numberOfHealth;
    private final Rectangle bombFull;
    private final Text text;
    private final miniBoss[] miniBosses;

    public bombAnimation(bomb bomb, boss boss, plane plane, Rectangle bombFull, Rectangle health, Text numberOfHealth, Text text, miniBoss[] miniBosses)
    {
        this.bomb = bomb;
        this.boss = boss;
        this.plane = plane;
        this.health = health;
        this.bombFull = bombFull;
        this.text = text;
        this.miniBosses = miniBosses;
        this.numberOfHealth = numberOfHealth;
        setCycleDuration(Duration.millis(1000));
        setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        bomb.getImageView().setX(bomb.getImageView().getX() + 10);
        bomb.getImageView().setY(bomb.getImageView().getY() + 19);
        if(inContactWithBoss()) {
            bomb.getImageView().setImage(null);
            boss.setHealth(boss.getHealth() - 18);
            plane.setBombFull(plane.getBombFull() + 1);
            if(bombFull.getWidth() < 101)
                bombFull.setWidth(bombFull.getWidth() + 1);
            if(bombFull.getWidth() == 101)
                text.setText("bomb is ready :)");
            health.setWidth(health.getWidth() - 0.9);
            numberOfHealth.setText(String.valueOf((Integer.parseInt(numberOfHealth.getText()) - 18)));
            attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(bomb.getImageView(), 60, -10, 45, 45);
            attackBoomAnimation.play();
            pause();
        }
        if(inContactWithBall()) {
            bomb.getImageView().setImage(null);
            boss.getBall().setHealth(boss.getBall().getHealth() - 1);
            if(boss.getBall().getHealth() == 0) {
                boss.stopBallAnimation();
                boss.getBall().getImageView().setImage(null);
                boss.rePositionBall();
                boss.getBall().setHealth(3);
            }
            attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(bomb.getImageView(), 60, -10, 45, 45);
            attackBoomAnimation.play();
            pause();
        }
        if(inContactWithMiniBoss(miniBosses) != null) {
            miniBoss tmp = inContactWithMiniBoss(miniBosses);
            bomb.getImageView().setImage(null);
            assert tmp != null;
            tmp.setHealth(tmp.getHealth() - 1);
            if(tmp.getHealth() == 0) {
                tmp.getImageView().setX(1280 + indexOfMiniBoss(tmp) * 75);
                tmp.getImageView().setImage(null);
                tmp.setHealth(3);
            }
            attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(bomb.getImageView(), 60, -10, 45, 45);
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
        return bomb.getLayoutBounds().intersects(boss.getImageView().getLayoutBounds());
    }
    private boolean inContactWithBall()
    {
        return bomb.getLayoutBounds().intersects(boss.getBall().getImageView().getLayoutBounds());
    }
    private miniBoss inContactWithMiniBoss(miniBoss[] miniBosses)
    {
        for(int i = 0; i < 4; i++)
            if (bomb.getImageView().getBoundsInParent().intersects(miniBosses[i].getImageView().getLayoutBounds()))
                return miniBosses[i];
        return null;
    }
}

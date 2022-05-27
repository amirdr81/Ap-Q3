package transition;

import Model.Menu.miniBoss;
import Model.boss;
import Model.plane;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;


public class planeAnimation extends Transition {
    private final plane plane;
    private final boss boss;
    private final miniBoss[] miniBosses;
    private final Text numberOfPlaneHealth;
    private final Rectangle health;
    private final ImageView planeCrash;
    private int theta = 0;

    public planeAnimation(plane plane, boss boss, Text numberOfPlaneHealth, Rectangle health, ImageView planeCrash, miniBoss[] miniBosses)
    {
        this.plane = plane;
        this.boss = boss;
        this.miniBosses = miniBosses;
        this.numberOfPlaneHealth = numberOfPlaneHealth;
        this.health = health;
        this.planeCrash = planeCrash;
        this.setCycleDuration(Duration.millis(50));
        this.setCycleCount(1);
    }

    @Override
    protected void interpolate(double v) {
        int speed = 10;
        double dx = speed * Math.cos(Math.toRadians(theta));
        double dy = -1 * speed * Math.sin(Math.toRadians(theta));
        if(inContactWithBall() && !plane.isBlink())
            contactCrash();
        if(inContactWithMiniBoss() != null && Objects.requireNonNull(inContactWithMiniBoss()).getImageView() != null && !plane.isBlink())
            miniBossCrash(Objects.requireNonNull(inContactWithMiniBoss()));
        if((plane.getImageView().getX() >= 10 && dx < 0) || (plane.getImageView().getX() <= 1160 && dx > 0)) {
            plane.getImageView().setX(plane.getImageView().getX() + dx);
            if(inContactWithBoss() && !plane.isBlink()) {
                plane.getImageView().setX(plane.getImageView().getX() - dx);
                contactCrash();
            }
        }
        if((plane.getImageView().getY() >= 10 && dy < 0) || (plane.getImageView().getY() <= 620 && dy > 0)) {
            plane.getImageView().setY(plane.getImageView().getY() + dy);
            if(inContactWithBoss() && !plane.isBlink()) {
                plane.getImageView().setY(plane.getImageView().getY() - dy);
                contactCrash();
            }
        }
    }
    private boolean inContactWithBoss()
    {
        return plane.getImageView().getBoundsInParent().intersects(boss.getImageView().getLayoutBounds());
    }
    private boolean inContactWithBall()
    {
        return plane.getImageView().getBoundsInParent().intersects(boss.getBall().getImageView().getLayoutBounds());
    }
    private miniBoss inContactWithMiniBoss()
    {
        for(int i = 0; i < 4; i++)
            if (plane.getImageView().getBoundsInParent().intersects(miniBosses[i].getImageView().getLayoutBounds()))
                return miniBosses[i];
        return null;
    }
    public void setTheta(int theta)
    {
        this.theta = theta;
    }
    private void reDirectPlane()
    {
        plane.getImageView().setX(50);
        plane.getImageView().setY(300);
    }
    private void contactCrash() {
        plane.setHealth(plane.getHealth() - 1);
        numberOfPlaneHealth.setText(String.valueOf((Integer.parseInt(numberOfPlaneHealth.getText()) - 1)));
        health.setWidth(health.getWidth() - 12.5);
        planeCrash.setX(boss.getImageView().getX());
        planeCrash.setY(boss.getImageView().getY() + 100);
        attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(planeCrash, 0, 100, 150, 150);
        attackBoomAnimation.play();
        reDirectPlane();
        blinkPlaneAnimation blinkPlaneAnimation = new blinkPlaneAnimation(plane);
        plane.setBlink();
        blinkPlaneAnimation.play();
    }
    private void miniBossCrash(miniBoss miniBoss) {
        miniBoss.getImageView().setImage(null);
        plane.setHealth(plane.getHealth() - 1);
        numberOfPlaneHealth.setText(String.valueOf((Integer.parseInt(numberOfPlaneHealth.getText()) - 1)));
        health.setWidth(health.getWidth() - 12.5);
        planeCrash.setX(plane.getImageView().getX());
        planeCrash.setY(plane.getImageView().getY() + 100);
        attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(planeCrash, 0, 100, 150, 150);
        attackBoomAnimation.play();
        reDirectPlane();
        blinkPlaneAnimation blinkPlaneAnimation = new blinkPlaneAnimation(plane);
        plane.setBlink();
        blinkPlaneAnimation.play();
    }
}

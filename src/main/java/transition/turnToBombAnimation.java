package transition;

import Model.boss;
import Model.plane;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;

public class turnToBombAnimation extends Transition {
    private final plane plane;
    private final boss boss;
    private final Rectangle health;
    private final Image[] images = new Image[39];
    private final Text numberOfHealth;
    private int frameCounter = 0;

    public turnToBombAnimation(plane plane, boss boss, Rectangle health, Text numberOfHealth)
    {
        this.plane = plane;
        this.boss = boss;
        this.health = health;
        this.numberOfHealth = numberOfHealth;
        for (int i = 0; i < 12; i++)
            images[i] = new Image("photos/bullet/getReadyTurnBomb.png");
        images[12] = new Image("photos/bullet/newBomb.png");
        for(int i = 13; i < 18; i++)
            images[i] = new Image("photos/bombAttack/1.png");
        for(int i = 18; i < 23; i++)
            images[i] = new Image("photos/bombAttack/2.png");
        for(int i = 23; i < 28; i++)
            images[i] = new Image("photos/bombAttack/3.png");
        for(int i = 28; i < 33; i++)
            images[i] = new Image("photos/bombAttack/4.png");
        for(int i = 33; i < 38; i++)
            images[i] = new Image("photos/bombAttack/5.png");
        images[38] = plane.getImageView().getImage();
        setCycleDuration(Duration.seconds(3));
        setCycleCount(1);
    }
    @Override
    protected void interpolate(double v) {
        plane.getImageView().setImage(images[frameCounter]);
        if(frameCounter <= 12) {
            plane.getImageView().setFitWidth(100);
            plane.getImageView().setFitHeight(100);
        }
        else if(frameCounter == 13) {
            plane.getImageView().setX(plane.getImageView().getX() - 100);
            plane.getImageView().setY(plane.getImageView().getY() - 100);
            plane.getImageView().setFitWidth(400);
            plane.getImageView().setFitHeight(400);
        }
        else {
            plane.getImageView().setFitWidth(400);
            plane.getImageView().setFitHeight(400);
        }
        if(frameCounter != 12) frameCounter++;
        if(frameCounter == 12) {
            plane.getImageView().setImage(images[12]);
            plane.getImageView().setX(plane.getImageView().getX() + 10);
            if(plane.getImageView().getBoundsInParent().intersects(boss.getImageView().getLayoutBounds()))
                frameCounter++;
        }
        if(frameCounter == 39) {
            plane.setScore(plane.getScore() + 1);
            plane.getImageView().setX(plane.getImageView().getX() + 100);
            plane.getImageView().setY(plane.getImageView().getY() + 100);
            plane.getImageView().setFitWidth(100);
            plane.getImageView().setFitHeight(100);
            boss.setHealth(boss.getHealth() - 36);
            health.setWidth(health.getWidth() - 1.8);
            numberOfHealth.setText(String.valueOf((Integer.parseInt(numberOfHealth.getText()) - 36)));
            plane.setBlink();
            plane.getImageView().setX(plane.getImageView().getX() - 200);
            pause();
        }
    }
}

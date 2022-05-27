import Model.*;
import Model.Menu.Menu;
import Model.Menu.miniBoss;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import transition.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class NewGame extends Application {

    public ImageView plane;
    public ImageView boss;
    public ImageView ground;
    public ImageView cloud;
    public Pane pane;
    public ImageView upperClouds;
    public ImageView upperClouds2;
    public ImageView cloud2;
    public ImageView ground2;
    public ImageView shootType;
    public Rectangle bossHealth;
    public Text bossNumberOfHealth;
    public Text planeNumberOfHealth;
    public Rectangle planeHealth;
    public ImageView planeCrash;
    public Rectangle bombFull;
    public Text readyBomb;
    public ImageView miniP1;
    public ImageView miniP2;
    public ImageView miniP3;
    public ImageView miniP4;
    private int tmp = 1;
    private boolean typeOfShoot = true;
    private int flag = 0;
    private boss bossModel;
    private plane planeModel;
    private int timeCounter = 0;
    private final ImageView[] purpleMini = new ImageView[4];
    private miniBoss[] miniBosses;
    public AudioClip audioClip = new AudioClip(getClass().getResource("audio/gameSound.mp3").toExternalForm());
    public void initialize() throws InterruptedException {
        miniBosses = new miniBoss[4];
        miniBosses[0] = new miniBoss(miniP1);
        miniBosses[1] = new miniBoss(miniP2);
        miniBosses[2] = new miniBoss(miniP3);
        miniBosses[3] = new miniBoss(miniP4);
        purpleMini[0] = miniP1;
        purpleMini[1] = miniP2;
        purpleMini[2] = miniP3;
        purpleMini[3] = miniP4;
        miniBossAnimation miniBossAnimation = new miniBossAnimation(purpleMini);
        AudioClip shotAudio = new AudioClip(getClass().getResource("audio/shot.wav").toExternalForm());
        AudioClip changeAudio = new AudioClip(getClass().getResource("audio/attack.wav").toExternalForm());
        backgroundAnimations();
        bossModel = new boss(boss);
        planeModel = new plane(plane);
        if(Menu.loggedInUser.getAvatar().equals("red"))
            planeModel.getImageView().setImage(new Image("photos/avatart/red.png"));
        if(Menu.loggedInUser.getAvatar().equals("blue"))
            planeModel.getImageView().setImage(new Image("photos/avatart/blue.png"));
        pane.getChildren().add(6, bossModel.getBall().getImageView());
        bossAnimation bossAnimation = new bossAnimation(bossModel);
        bossAnimation.play();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(inContactWithBall() && !planeModel.isBlink())
                    contactCrash();
                if(inContactWithBoss() && !planeModel.isBlink())
                    contactCrash();
                if(inContactWithMiniBoss() != null && Objects.requireNonNull(inContactWithMiniBoss()).getImageView() != null && !planeModel.isBlink())
                    miniBossCrash(Objects.requireNonNull(inContactWithMiniBoss()));
                if (planeHealth.getWidth() <= 50) //crisis health amount
                    planeHealth.setStyle("-fx-fill: #d9d914");
                planeModel.getImageView().setX(planeModel.getImageView().getX() + tmp);
                planeModel.getImageView().setY(planeModel.getImageView().getY() + tmp);
                timeCounter++;
                if(timeCounter % 100 == 0) {
                    for(int i = 0; i < 4; i++) {
                        purpleMini[i].setImage(new Image(miniBossAnimation.getFrame1()));
                        purpleMini[i].setX(1280 + i * 75);
                        purpleMini[i].setY(60 + ((purpleMini[i].getY() + 123) % 600));
                    }
                    miniBossAnimation.play();
                }
                tmp *= -1;
            }
        }, 0, 100);
        plane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(flag == 0 && Integer.parseInt(bossNumberOfHealth.getText()) <= 2500) {
                    flag = 1;
                    bossAnimation.pause();
                    bossModel.getImageView().setY(350);
                    devilModeP2Animation devilModeP2Animation = new devilModeP2Animation(bossModel);
                    devilModeP2Animation.play();
                }
                String keyName = keyEvent.getCode().getName();
                planeAnimation planeAnimation = new planeAnimation(planeModel, bossModel, planeNumberOfHealth, planeHealth, planeCrash, miniBosses);
                switch (keyName) {
                    case "W":
                        if (plane.getY() >= 10) {
                            planeAnimation.setTheta(90);
                            planeAnimation.play();
                        }
                        break;
                    case "S":
                        if (plane.getY() <= 620) {
                            planeAnimation.setTheta(270);
                            planeAnimation.play();
                        }
                        break;
                    case "A":
                        if (plane.getX() >= 10) {
                            planeAnimation.setTheta(180);
                            planeAnimation.play();
                        }
                        break;
                    case "D":
                        if (plane.getX() <= 1160) {
                            planeAnimation.setTheta(0);
                            planeAnimation.play();
                        }
                        break;
                    case "R":
                        if(bombFull.getWidth() >= 101 && !planeModel.isBlink()) {
                            readyBomb.setText("bomb is not ready :(");
                            changeAudio.play();
                            planeModel.setBombFull(0);
                            planeModel.setBlink();
                            turnToBombAnimation turnToBombAnimation = new turnToBombAnimation(planeModel, bossModel, bossHealth, bossNumberOfHealth);
                            turnToBombAnimation.play();
                            bombFull.setWidth(1);
                        }
                        break;
                    case "Tab":
                        changeAudio.play();
                        changeShootType();
                        break;
                    case "Space":
                        if (typeOfShoot) //normal shoot
                        {
                            shotAudio.play();
                            bullet bullet = new bullet(plane.getX() + 100, plane.getY() + 40);
                            ImageView bulletExplosion = new ImageView();
                            bulletExplosionAnimation bulletExplosionAnimation = new bulletExplosionAnimation(bulletExplosion, plane);
                            pane.getChildren().add(bulletExplosion);
                            pane.getChildren().add(bullet.getImageView());
                            bulletAnimation bulletAnimation = new bulletAnimation(bullet, bossModel, bossHealth, bombFull, bossNumberOfHealth, planeModel, readyBomb, miniBosses);
                            bulletAnimation.play();
                            bulletExplosionAnimation.play();
                        } else //bomb
                        {
                            shotAudio.play();
                            bomb bomb = new bomb(plane.getX() + 100, plane.getY() + 40);
                            pane.getChildren().add(bomb.getImageView());
                            bombAnimation bombAnimation = new bombAnimation(bomb, bossModel, planeModel, bombFull, bossHealth, bossNumberOfHealth, readyBomb, miniBosses);
                            bombAnimation.play();
                        }
                        break;
                    case "Esc":
                        Platform.exit();
                        break;
                }
            }
        });
    }

    private void changeShootType()
    {
        if(typeOfShoot)
            shootType.setImage(new Image("photos/bullet/shootType/bomb.png"));
        else
            shootType.setImage(new Image("photos/bullet/shootType/normal.png"));
        typeOfShoot = !typeOfShoot;

    }
    private void backgroundAnimations()
    {
        backgroundAnimation groundAnimation = new backgroundAnimation(ground, 2);
        groundAnimation.getBackground().setX(0);
        backgroundAnimation cloudAnimation = new backgroundAnimation(cloud, 1.7);
        cloudAnimation.getBackground().setX(0);
        backgroundAnimation upperCloud = new backgroundAnimation(upperClouds, 2);
        upperCloud.getBackground().setX(0);
        backgroundAnimation groundAnimation2 = new backgroundAnimation(ground2, 2);
        groundAnimation2.getBackground().setX(1280);
        backgroundAnimation cloudAnimation2 = new backgroundAnimation(cloud2, 1.7);
        cloudAnimation2.getBackground().setX(1280);
        backgroundAnimation upperCloud2 = new backgroundAnimation(upperClouds2, 2);
        upperCloud2.getBackground().setX(1280);
        groundAnimation.play();
        groundAnimation2.play();
        cloudAnimation.play();
        cloudAnimation2.play();
        upperCloud.play();
        upperCloud2.play();
    }
    private boolean inContactWithBoss()
    {
        return plane.getBoundsInParent().intersects(boss.getLayoutBounds());
    }
    private boolean inContactWithBall()
    {
        return plane.getBoundsInParent().intersects(bossModel.getBall().getImageView().getLayoutBounds());
    }
    private miniBoss inContactWithMiniBoss()
    {
        for(int i = 0; i < 4; i++)
            if (planeModel.getImageView().getBoundsInParent().intersects(miniBosses[i].getImageView().getLayoutBounds()))
                return miniBosses[i];
        return null;
    }
    private void reDirectPlane()
    {
        plane.setX(50);
        plane.setY(300);
    }
    private void contactCrash() {
        planeModel.setHealth(planeModel.getHealth() - 1);
        planeNumberOfHealth.setText(String.valueOf((Integer.parseInt(planeNumberOfHealth.getText()) - 1)));
        planeHealth.setWidth(planeHealth.getWidth() - 12.5);
        planeCrash.setX(bossModel.getImageView().getX());
        planeCrash.setY(bossModel.getImageView().getY() + 100);
        attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(planeCrash, 0, 100, 150, 150);
        attackBoomAnimation.play();
        reDirectPlane();
        blinkPlaneAnimation blinkPlaneAnimation = new blinkPlaneAnimation(planeModel);
        planeModel.setBlink();
        blinkPlaneAnimation.play();
    }
    private void miniBossCrash(miniBoss miniBoss) {
        miniBoss.getImageView().setImage(null);
        planeModel.setHealth(planeModel.getHealth() - 1);
        planeNumberOfHealth.setText(String.valueOf((Integer.parseInt(planeNumberOfHealth.getText()) - 1)));
        planeHealth.setWidth(planeHealth.getWidth() - 12.5);
        planeCrash.setX(planeModel.getImageView().getX());
        planeCrash.setY(planeModel.getImageView().getY() + 100);
        attackBoomAnimation attackBoomAnimation = new attackBoomAnimation(planeCrash, 0, 100, 150, 150);
        attackBoomAnimation.play();
        reDirectPlane();
        blinkPlaneAnimation blinkPlaneAnimation = new blinkPlaneAnimation(planeModel);
        planeModel.setBlink();
        blinkPlaneAnimation.play();
    }
    @Override
    public void start(Stage stage) throws Exception {
        main.audioClip.stop();
        audioClip.play();
        menuController.playChangeMenu();
        Pane root = FXMLLoader.load(new URL(getClass().getResource("fxml/newGame.fxml").toExternalForm()));
        stage.setScene(new Scene(root));
        root.getChildren().get(root.getChildren().size() - 1).requestFocus();
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}



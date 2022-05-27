import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.net.URL;

public class main extends Application{
    public static AudioClip audioClip = new AudioClip(main.class.getResource("audio/backingTrack.mp3").toExternalForm());
    @Override
    public void start(Stage stage) throws Exception {
        audioClip.play();
        menuController.updateDatabase();
        URL address = new URL(getClass().getResource("fxml/loginPage.fxml").toExternalForm());
        Parent root1 = FXMLLoader.load(address);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

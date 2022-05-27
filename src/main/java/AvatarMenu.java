
import Model.Menu.Menu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class AvatarMenu extends Application {
    public VBox list;
    public ImageView imageView;
    public Button mute;

    private void enterMenu(MouseEvent mouseEvent, String name) throws IOException {
        AudioClip audioClip = new AudioClip(getClass().getResource("audio/changeMenu.wav").toExternalForm());
        audioClip.play();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        URL address = new URL(ProfilePage.class.getResource(name).toExternalForm());
        Parent root = FXMLLoader.load(address);
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void profileMenu(MouseEvent mouseEvent) throws IOException {
        enterMenu(mouseEvent, "fxml/profilePage.fxml");
    }
    public void blueChosen(MouseEvent mouseEvent) throws IOException {
        Menu.loggedInUser.setAvatar("blue");
        if(!Menu.loggedInUser.getUsername().equals(""))
            menuController.writeDataOnJson();
        profileMenu(mouseEvent);
    }
    public void redChosen(MouseEvent mouseEvent) throws IOException {
        Menu.loggedInUser.setAvatar("red");
        if(!Menu.loggedInUser.getUsername().equals(""))
            menuController.writeDataOnJson();
        profileMenu(mouseEvent);
    }
    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }
    @Override
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        Pane root = FXMLLoader.load(new URL(getClass().getResource("fxml/avatarMenu.fxml").toExternalForm()));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

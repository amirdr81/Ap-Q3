import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.net.URL;

public class EndGame extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        URL address = new URL(getClass().getResource("fxml/endGame.fxml").toExternalForm());
        Pane root = FXMLLoader.load(address);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

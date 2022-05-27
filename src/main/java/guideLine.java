import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class guideLine extends Application {
    public VBox list;

    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        Pane root = FXMLLoader.load(new URL(Objects.requireNonNull(getClass().getResource("fxml/guideLine.fxml")).toExternalForm()));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        NewGame newGame = new NewGame();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        newGame.start(stage);
    }
}

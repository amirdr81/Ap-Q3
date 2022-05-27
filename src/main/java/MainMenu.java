import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;

public class MainMenu extends Application {
    public static VBox list;
    public Button mute;

    public void playGame(MouseEvent mouseEvent) throws Exception {
        guideLine guideLine = new guideLine();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        guideLine.start(stage);
    }
    public void profileMenu(MouseEvent mouseEvent) throws Exception {
        ProfilePage profilePage = new ProfilePage();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        profilePage.start(stage);
    }
    public void scoreBoard(MouseEvent mouseEvent) throws Exception {
        ScoreBoard scoreBoard = new ScoreBoard();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scoreBoard.start(stage);
    }
    public void logout(MouseEvent mouseEvent) throws Exception {
        LoginPage loginPage = new LoginPage();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        loginPage.start(stage);
    }

    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }
    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        URL address = new URL(getClass().getResource("fxml/mainMenu.fxml").toExternalForm());
        Parent root = FXMLLoader.load(address);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

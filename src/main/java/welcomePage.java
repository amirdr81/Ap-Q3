import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;

public class welcomePage extends Application {
    public void loginPage(MouseEvent mouseEvent) throws Exception {
        LoginPage loginPage = new LoginPage();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        loginPage.start(stage);
    }
    @Override
    public void start(Stage stage) throws Exception {

    }
    public static void main(String[] args) {
        launch(args);
    }
    public void pauseGame(MouseEvent mouseEvent) throws Exception {
        EndGame endGame = new EndGame();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        endGame.start(stage);
    }

}

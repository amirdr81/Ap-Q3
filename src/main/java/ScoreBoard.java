import Model.Menu.Menu;
import Model.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Comparator;

public class ScoreBoard extends Application {
    public VBox list;
    public Button mute;

    public void showScoreBoard(MouseEvent mouseEvent) {
        menuController.playChangeMenu();
        ObservableList<Node> tmp = list.getChildren();
        if(tmp.size() == 12) {
            Menu.allUsers.sort(Comparator.comparing(User::getScore));
            int size = Menu.allUsers.size();
            if(size > 10) {
                for (int i = 9; i >= 0; i--) {
                    tmp.add((size - i + 4), new Label("player " + (size - i) + " : " +
                            Menu.allUsers.get(i).getUsername() + " - score: " + Menu.allUsers.get(i).getScore()));
                    tmp.get(size - i + 4).setStyle("-fx-font-size: 25; -fx-text-fill: white; -fx-alignment: center;" +
                            "-fx-background-color: #644a4a; -fx-max-height: 30;-fx-max-width: 500;" +
                            "-fx-border-color: black; -fx-border-width: 5");
                }
            }
            else {
                for (int i = size - 1; i >= 0; i--) {
                    tmp.add((size - i + 4), new Label("player " + (size - i) + " : " +
                            Menu.allUsers.get(i).getUsername() + " - score: " + Menu.allUsers.get(i).getScore()));
                    tmp.get(size - i + 4).setStyle("-fx-font-size: 25; -fx-text-fill: white; -fx-alignment: center;" +
                            "-fx-background-color: #644a4a; -fx-max-height: 30;-fx-max-width: 500;" +
                            "-fx-border-color: black; -fx-border-width: 5");
                }
            }
            if(tmp.size() > 14) {
                for(int i = 5; i < 8; i++)
                    tmp.get(i).setStyle("-fx-font-size: 35; -fx-text-fill: white; -fx-alignment: center;" +
                            "-fx-background-color: #cb0e0e; -fx-max-height: 30;-fx-max-width: 500;" +
                            "-fx-border-color: black; -fx-border-width: 5");
            }

        }
        else
            for(int i = 0; i < Menu.allUsers.size(); i++)
                tmp.remove(5);

    }
    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }
    public void mainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        mainMenu.start(stage);
    }

    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        Pane root = FXMLLoader.load(new URL(getClass().getResource("fxml/scoreBoard.fxml").toExternalForm()));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

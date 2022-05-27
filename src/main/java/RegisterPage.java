import Model.Menu.Menu;
import Model.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class RegisterPage extends Application {
    public VBox list;
    public TextField username;
    public TextField password;
    public Button mute;

    private User getUserByUsername(String username)
    {
        for(User user : Menu.allUsers)
            if(user.getUsername().equals(username)) return user;
        return null;
    }
    public boolean checkWeaknessOfPassword() {
        //check length
        if (password.getText().length() < 5) return true;
        //checking weak
        int flag = 0, flag1 = 0, flag2 = 0, flag3 = 0;
        for (int i = 0; i < password.getText().length(); i++) {
            char tmp = password.getText().charAt(i);
            if (flag1 == 0 && (tmp > 64 && tmp < 91)) {
                flag1 = 1;
                flag++;
            }
            if (flag2 == 0 && (tmp > 96 && tmp < 123)) {
                flag2 = 1;
                flag++;
            }
            if (flag3 == 0 && (tmp > 46 && tmp < 58)) {
                flag3 = 1;
                flag++;
            }
        }
        return flag != 3;
    }
    public void passwordWeakness(KeyEvent keyEvent) {
        if (checkWeaknessOfPassword())
            password.setStyle("-fx-border-color: red; -fx-border-width: 3");
        else
            password.setStyle("-fx-border-color: green; -fx-border-width: 3");
    }
    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }
    public void addTask(MouseEvent mouseEvent) throws Exception {
        ObservableList<Node> tmp = list.getChildren();
        if(username.getText().equals("") && tmp.size() != 8) {
            tmp.remove(6);
            tmp.add(6, new Text("please enter username"));
            tmp.get(6).setStyle("-fx-fill: red");
        }
        else if(username.getText().equals("")) {
            tmp.add(6, new Text("please enter username"));
            tmp.get(6).setStyle("-fx-fill: red");
        } else if(password.getText().equals("") && tmp.size() != 8) {
            tmp.remove(6);
            tmp.add(6, new Text("please enter password"));
            tmp.get(6).setStyle("-fx-fill: red");
        } else if(password.getText().equals("")) {
            tmp.add(6, new Text("please enter password"));
            tmp.get(6).setStyle("-fx-fill: red");
        } else if(checkWeaknessOfPassword() && tmp.size() != 8) {
            tmp.remove(6);
            tmp.add(6, new Text("password is weak"));
            tmp.get(6).setStyle("-fx-fill: red");
        } else if(checkWeaknessOfPassword()) {
            tmp.add(6, new Text("password is weak"));
            tmp.get(6).setStyle("-fx-fill: red");
        } else {
            if (tmp.size() != 8)
                tmp.remove(6);
            if(getUserByUsername(username.getText()) != null && tmp.size() != 8) {
                tmp.remove(6);
                tmp.add(6, new Text("there is already a user with this username"));
                tmp.get(6).setStyle("-fx-fill: red");
            } else if (getUserByUsername(username.getText()) != null) {
                tmp.add(6, new Text("there is already a user with this username"));
                tmp.get(6).setStyle("-fx-fill: red");
            } else {
                User newUser = new User(username.getText(), password.getText(), "blue");
                Menu.loggedInUser = newUser;
                Menu.allUsers.add(newUser);
                menuController.writeDataOnJson();
                MainMenu mainMenu = new MainMenu();
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                mainMenu.start(stage);
            }
        }
    }
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        BorderPane root = FXMLLoader.load(new URL(NewGame.class.getResource("fxml/registerPage.fxml").toExternalForm()));
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

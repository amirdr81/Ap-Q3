import Model.Menu.Menu;
import Model.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;

public class NewUsername extends Application {
    public TextField currUsername;
    public TextField newUsername;
    public VBox list;
    public Button mute;

    private User getUserByUsername(String username)
    {
        for(User user : Menu.allUsers)
            if(user.getUsername().equals(username)) return user;
        return null;
    }

    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }

    public void profileMenu(MouseEvent mouseEvent) throws Exception {
        ProfilePage profilePage = new ProfilePage();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        profilePage.start(stage);
    }

    public void addTask(MouseEvent mouseEvent) throws Exception {
        ObservableList<Node> tmp = list.getChildren();
        if(Menu.loggedInUser == null && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("you have logged in as guest"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(Menu.loggedInUser == null) {
            tmp.add(7, new Text("you have logged in as guest"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(currUsername.getText().equals("") && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("please enter current username"));
            tmp.get(7).setStyle("-fx-fill: red");
        }
        else if(currUsername.getText().equals("")) {
            tmp.add(7, new Text("please enter current username"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(newUsername.getText().equals("") && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("please enter new username"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(newUsername.getText().equals("")) {
            tmp.add(7, new Text("please enter new username"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(currUsername.getText().equals(newUsername.getText()) && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("new username and current username can't be equal"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(currUsername.getText().equals(newUsername.getText())) {
            tmp.add(7, new Text("new username and current username can't be equal"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(!Menu.loggedInUser.getUsername().equals(currUsername.getText()) && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("username is not correct"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(!Menu.loggedInUser.getUsername().equals(currUsername.getText())) {
            tmp.add(7, new Text("please enter new username"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(getUserByUsername(newUsername.getText()) != null && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("there is a user with this username"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(getUserByUsername(newUsername.getText()) != null) {
            tmp.add(7, new Text("there is a user with this username"));
            tmp.get(7).setStyle("-fx-fill: red");
        }else {
            if (tmp.size() != 9)
                tmp.remove(7);
            getUserByUsername(currUsername.getText()).setUsername(newUsername.getText());
            menuController.writeDataOnJson();
            profileMenu(mouseEvent);
        }
    }
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        URL address_login_page = new URL(getClass().getResource("fxml/newUsername.fxml").toExternalForm());
        Parent root1 = FXMLLoader.load(address_login_page);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

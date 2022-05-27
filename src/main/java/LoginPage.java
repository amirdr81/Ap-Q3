import java.net.URL;
import java.util.Objects;
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

public class LoginPage extends Application {

    public TextField username;
    public TextField password;
    public VBox list;
    public Button mute;

    private User getUserByUsername(String username)
    {
        for(User user : Menu.allUsers)
            if(user.getUsername().equals(username)) return user;
        return null;
    }
    private boolean matchPassword(User user, String password)
    {
        return !user.getPassword().equals(password);
    }
    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }
    public void registerMenu(MouseEvent mouseEvent) throws Exception {
        RegisterPage registerPage = new RegisterPage();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        registerPage.start(stage);
    }
    public void mainMenu(MouseEvent mouseEvent) throws Exception {
        if(Menu.loggedInUser == null)
            Menu.loggedInUser = new User("", "123aA", "blue");
        MainMenu mainMenu = new MainMenu();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        mainMenu.start(stage);
    }
    public void addTask(MouseEvent mouseEvent) throws Exception {
        ObservableList<Node> tmp = list.getChildren();
        if(username.getText().equals("") && tmp.size() != 11) {
            tmp.remove(7);
            tmp.add(7, new Text("please enter username"));
            tmp.get(7).setStyle("-fx-fill: red");
        }
        else if(username.getText().equals("")) {
            tmp.add(7, new Text("please enter username"));
            tmp.get(7).setStyle("-fx-fill: red");
        }
        else if(password.getText().equals("") && tmp.size() != 11) {
            tmp.remove(7);
            tmp.add(7, new Text("please enter password"));
            tmp.get(7).setStyle("-fx-fill: red");
        }
        else if(password.getText().equals("")) {
            tmp.add(7, new Text("please enter password"));
            tmp.get(7).setStyle("-fx-fill: red");
        }
        else {
            if (tmp.size() != 11)
                tmp.remove(7);
            if(getUserByUsername(username.getText()) == null && tmp.size() != 11) {
                tmp.remove(7);
                tmp.add(7, new Text("there is no user with this username"));
                tmp.get(7).setStyle("-fx-fill: red");
            } else if (getUserByUsername(username.getText()) == null) {
                tmp.add(7, new Text("there is no user with this username"));
                tmp.get(7).setStyle("-fx-fill: red");
            } else if (matchPassword(Objects.requireNonNull(getUserByUsername(username.getText())), password.getText()) && tmp.size() != 11) {
                tmp.remove(7);
                tmp.add(7, new Text("password is incorrect"));
                tmp.get(7).setStyle("-fx-fill: red");
            } else if (matchPassword(Objects.requireNonNull(getUserByUsername(username.getText())), password.getText())) {
                tmp.add(7, new Text("password is incorrect"));
                tmp.get(7).setStyle("-fx-fill: red");
            }
            else {
                Menu.loggedInUser = getUserByUsername(username.getText());
                mainMenu(mouseEvent);
            }
        }
    }
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        URL address_login_page = new URL(Objects.requireNonNull(getClass().getResource("fxml/loginPage.fxml")).toExternalForm());
        Parent root1 = FXMLLoader.load(address_login_page);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

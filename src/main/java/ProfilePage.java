import Model.Menu.Menu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;

public class ProfilePage extends Application {
    public VBox list;
    public Button mute;

    public void mute(MouseEvent mouseEvent) {
        menuController.mute(mute);
    }
    public void newUsernameMenu(MouseEvent mouseEvent) throws Exception {
        NewUsername newUsername = new NewUsername();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        newUsername.start(stage);
    }

    public void newPasswordMenu(MouseEvent mouseEvent) throws Exception {
        NewPassword newPassword = new NewPassword();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        newPassword.start(stage);
    }

    public void backToMainMenu(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        mainMenu.start(stage);
    }
    public void avatarMenu(MouseEvent mouseEvent) throws Exception {
        AvatarMenu avatarMenu = new AvatarMenu();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        avatarMenu.start(stage);
    }

    private void backedToLoginMenu(MouseEvent mouseEvent) throws Exception {
        LoginPage loginPage = new LoginPage();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        loginPage.start(stage);
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception {
        if(!Menu.loggedInUser.getUsername().equals("")) {
            Menu.allUsers.remove(Menu.loggedInUser);
            Menu.loggedInUser = null;
            menuController.writeDataOnJson();
            backedToLoginMenu(mouseEvent);
        }
    }
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        URL address_login_page = new URL(getClass().getResource("fxml/profilePage.fxml").toExternalForm());
        Parent root1 = FXMLLoader.load(address_login_page);
        stage.setScene(new Scene(root1));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

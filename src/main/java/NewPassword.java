import Model.Menu.Menu;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;

public class NewPassword extends Application {
    public TextField currPassword;
    public TextField newPassword;

    public VBox list;
    public Button mute;

    public boolean checkWeaknessOfPassword() {
        //check length
        if (newPassword.getText().length() < 5) return false;
        //checking weak
        int flag = 0, flag1 = 0, flag2 = 0, flag3 = 0;
        for (int i = 0; i < newPassword.getText().length(); i++) {
            char tmp = newPassword.getText().charAt(i);
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
        return flag == 3;
    }
    public void passwordWeakness(KeyEvent keyEvent) {
        if (!checkWeaknessOfPassword())
            newPassword.setStyle("-fx-border-color: red; -fx-border-width: 3");
        else
            newPassword.setStyle("-fx-border-color: green; -fx-border-width: 3");
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
        } else if(currPassword.getText().equals("") && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("please enter current password"));
            tmp.get(7).setStyle("-fx-fill: red");
        }
        else if(currPassword.getText().equals("")) {
            tmp.add(7, new Text("please enter current password"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(newPassword.getText().equals("") && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("please enter new password"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(newPassword.getText().equals("")) {
            tmp.add(7, new Text("please enter new password"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(currPassword.getText().equals(newPassword.getText()) && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("new password and current password can't be equal"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(currPassword.getText().equals(newPassword.getText())) {
            tmp.add(7, new Text("new password and current password can't be equal"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(!Menu.loggedInUser.getPassword().equals(currPassword.getText()) && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("current password is incorrect"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(!Menu.loggedInUser.getPassword().equals(currPassword.getText())) {
            tmp.add(7, new Text("current password is incorrect"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(!checkWeaknessOfPassword() && tmp.size() != 9) {
            tmp.remove(7);
            tmp.add(7, new Text("new password is weak"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else if(!checkWeaknessOfPassword()) {
            tmp.add(7, new Text("new password is weak"));
            tmp.get(7).setStyle("-fx-fill: red");
        } else {
            if (tmp.size() != 9)
                tmp.remove(7);
            Menu.loggedInUser.setPassword(newPassword.getText());
            menuController.writeDataOnJson();
            profileMenu(mouseEvent);
        }
    }
    public void start(Stage stage) throws Exception {
        menuController.playChangeMenu();
        URL address_login_page = new URL(getClass().getResource("fxml/newPassword.fxml").toExternalForm());
        Parent root1 = FXMLLoader.load(address_login_page);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

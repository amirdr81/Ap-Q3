import Model.User;
import Model.Menu.Menu;
import com.google.gson.Gson;
import enums.registerEnum;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.io.*;

public class menuController {
    private static final AudioClip audioClip = new AudioClip(menuController.class.getResource("audio/changeMenu.wav").toExternalForm());
    public static void mute(Button mute) {
        if(main.audioClip.isPlaying()) {
            mute.setText("unmute");
            main.audioClip.stop();
        }
        else {
            mute.setText("mute");
            main.audioClip.play();
        }
    }
    public static void adjustColor(ImageView imageView, int n)
    {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(n);
        imageView.setEffect(colorAdjust);
    }
    public static void playChangeMenu()
    {
        audioClip.play();
    }
    public static void updateDatabase()
    {
        try {
            Gson gson = new Gson();
            String arr = (new BufferedReader(new FileReader(registerEnum.filePath.regex))).readLine();
            if(arr != null)
            {
                arr = arr.substring(1,arr.length() - 1);
                String[] splitedArr = arr.split("},");
                for(int i = 0; i < splitedArr.length; i++)
                {
                    if(i != splitedArr.length - 1){
                        splitedArr[i] += "}";
                    }
                    Menu.allUsers.add(gson.fromJson(splitedArr[i], User.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//update arrayList with Json database

    public static void writeDataOnJson()
    {
        try {
            Writer writer = new FileWriter(registerEnum.filePath.regex);
            new Gson().toJson(Menu.allUsers, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//update Json database with arrayList

}

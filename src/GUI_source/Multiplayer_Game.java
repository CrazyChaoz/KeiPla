package GUI_source;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * Created by testuser on 09.05.2017.
 */
public class Multiplayer_Game {
    public Multiplayer_Game(){
        System.out.println("Starting the MultiGame");
        UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
        UI_FXML.currStage.setTitle("DAS SPIEL");
        Scene scene= null;
        try {
            scene = (new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setFill(Color.TRANSPARENT);
        UI_FXML.currStage.setScene(scene);
        UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        UI_FXML.currStage.show();
    }
}

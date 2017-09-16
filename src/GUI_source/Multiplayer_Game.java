package GUI_source;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/*


public class Multiplayer_Game{

    public Multiplayer_Game(){
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Starting the MultiGame");
                    UI_FXML.currStage.close();
                    UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                    UI_FXML.currStage.setTitle("DAS SPIEL");
                    Scene scene= null;
                    try {
                        scene = new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    scene.setFill(Color.TRANSPARENT);
                    scene.getStylesheets().addAll(UI_FXML.class.getResource(UI_FXML.theme).toExternalForm());
                    UI_FXML.currStage.setScene(scene);
                    UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                    UI_FXML.currStage.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
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


public class Multi_End {
    public Multi_End(){
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    UI_FXML.multiplayer = 0;
                    UI_FXML.currStage.close();
                    UI_FXML.currStage = new Stage(StageStyle.TRANSPARENT);
                    UI_FXML.currStage.setTitle("Score");
                    Scene s = null;
                    try {
                        s = new Scene(FXMLLoader.load(getClass().getResource("Multi_end.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    s.setFill(Color.TRANSPARENT);
                    s.getStylesheets().addAll(UI_FXML.class.getResource(UI_FXML.theme).toExternalForm());
                    UI_FXML.currStage.setScene(s);
                    UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res" + File.separator + "KeiPla-Icon-128.png")));
                    UI_FXML.currStage.show();
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

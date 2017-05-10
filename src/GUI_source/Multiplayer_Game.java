package GUI_source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;




public class Multiplayer_Game extends Application{
    public Multiplayer_Game(){
        try {
            this.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting the MultiGame");
        Stage stage=new Stage(StageStyle.TRANSPARENT);
        stage.setTitle("DAS SPIEL");
        Scene scene= null;
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.show();

    }
}

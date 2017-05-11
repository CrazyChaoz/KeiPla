package GUI_source;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;


public class UI_FXML {
    public static String NAME=null;
    public static Stage currStage=null;
    public static int score=0;
    public static int hardness=0;
    public static int running=0;
    public static int multiplayer=0;
    public static int lock=1;
    public static String multi_result=null;
    public static String[] currQuestion=new String[6];
    //##########################################
    //##########################################
    //##########################################
    public static void start_MainMenu(){
        Stage stage=new Stage();
        currStage=stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Menu");

        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(UI_FXML.class.getResource("MainMenu.fxml")));
        } catch (IOException e){}
        scene.setFill(Color.TRANSPARENT);

        stage.getIcons().add(new Image(UI_FXML.class.getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.setScene(scene);
        stage.show();
    }
}

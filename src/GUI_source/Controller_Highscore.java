package GUI_source;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_Highscore implements Initializable {
    private static String selected=null;
    private static double xOffset;
    private static double yOffset;

    @FXML
    private Button score1;
    @FXML
    private Button score2;
    @FXML
    private Button score3;
    @FXML
    private Button score4;
    @FXML
    private Button score5;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button title;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        setText();

        title.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = UI_FXML.currStage.getX() - event.getScreenX();
                yOffset = UI_FXML.currStage.getY() - event.getScreenY();
            }
        });

        title.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UI_FXML.currStage.setX(event.getScreenX() + xOffset);
                UI_FXML.currStage.setY(event.getScreenY() + yOffset);
            }
        });
        this.minimize.setOnAction(event -> {
            UI_FXML.currStage.setIconified(true);
        });
        this.close.setOnAction(event -> {
            UI_FXML.currStage.close();
        });

    }


    public void setText(){
        this.score1.setText(UI_FXML.currQuestion[0]);
        this.score2.setText(UI_FXML.currQuestion[1]);
        this.score3.setText(UI_FXML.currQuestion[2]);
        this.score4.setText(UI_FXML.currQuestion[3]);
        this.score5.setText(UI_FXML.currQuestion[4]);
    }
}

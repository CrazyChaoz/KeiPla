package GUI_source;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_ChooseMode implements Initializable {
    private int selected;
    private static double xOffset;
    private static double yOffset;

    @FXML
    private Button startgame;
    @FXML
    private Button ez;
    @FXML
    private Button mid;
    @FXML
    private Button hard;
    @FXML
    private Button minimize;
    @FXML
    private Button close;
    @FXML
    private Button title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        this.startgame.setOnAction(event -> {
            try {
                Logic.randomFilePicker(selected);
                UI_FXML.currStage.close();
                UI_FXML.currStage=new Stage(StageStyle.UNIFIED);
                UI_FXML.currStage.setTitle("DAS SPIEL");
                UI_FXML.currStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
            } catch(IOException e){}

        });*/

        this.ez.setOnAction(event -> selected=1);
        this.mid.setOnAction(event -> selected=2);
        this.hard.setOnAction(event -> selected=3);


        title.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
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
}

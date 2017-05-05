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
    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.startgame.setOnAction(event -> {
            try {
                new Question(selected);
                UI_FXML.hardness=selected;
                UI_FXML.currStage.close();
                UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                UI_FXML.currStage.setTitle("DAS SPIEL");
                UI_FXML.currStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
                UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                UI_FXML.currStage.show();
            } catch(IOException e){}

        });

        this.ez.setOnAction(event -> {
            selected=1;
            mid.setId("mittel");
            hard.setId("schwer");
            ez.setId("leichtCh");
        });
        this.mid.setOnAction(event -> {
            selected=2;
            ez.setId("leicht");
            hard.setId("schwer");
            mid.setId("mittelCh");
        });
        this.hard.setOnAction(event -> {
            selected=3;
            ez.setId("leicht");
            mid.setId("mittel");
            hard.setId("schwerCh");
        });


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
        this.back.setOnAction(event -> {
            try {
                UI_FXML.currStage.close();
                UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                UI_FXML.currStage.setTitle("Hauptmenü");
                UI_FXML.currStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
                UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                UI_FXML.currStage.show();
            } catch(IOException e){}
        });
    }
}

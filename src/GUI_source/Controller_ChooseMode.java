package GUI_source;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_ChooseMode extends Titlebar_Functionality implements Initializable {
    private int selected;

    @FXML
    private Button startgame;
    @FXML
    private Button ez;
    @FXML
    private Button mid;
    @FXML
    private Button hard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.startgame.setOnAction(event -> {
            try {
                System.out.println("Clicked on \"startgame\"");
                new Question(selected);
                UI_FXML.hardness=selected;
                UI_FXML.currStage.close();
                UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                UI_FXML.currStage.setTitle("DAS SPIEL");Scene s=(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
                s.setFill(Color.TRANSPARENT);
                UI_FXML.currStage.setScene(s);
                UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                UI_FXML.currStage.show();
            } catch(IOException e){}

        });

        this.ez.setOnAction(event -> {
            selected=1;
            mid.setId("mittel");
            hard.setId("schwer");
            ez.setId("leichtCh");
            System.out.println("Clicked on \"ez\"");
        });
        this.mid.setOnAction(event -> {
            selected=2;
            ez.setId("leicht");
            hard.setId("schwer");
            mid.setId("mittelCh");
            System.out.println("Clicked on \"mid\"");
        });
        this.hard.setOnAction(event -> {
            selected=3;
            ez.setId("leicht");
            mid.setId("mittel");
            hard.setId("schwerCh");
            System.out.println("Clicked on \"hard\"");
        });
        Titlebar_Functionality(this);
    }
}

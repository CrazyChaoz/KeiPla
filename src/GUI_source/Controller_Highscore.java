package GUI_source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_Highscore implements Initializable {
    private static double xOffset;
    private static double yOffset;

    @FXML
    private Label score1;
    @FXML
    private Label score2;
    @FXML
    private Label score3;
    @FXML
    private Label score4;
    @FXML
    private Label score5;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button title;
    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        setText();

        title.setOnMousePressed(event -> {
            xOffset = UI_FXML.currStage.getX() - event.getScreenX();
            yOffset = UI_FXML.currStage.getY() - event.getScreenY();
        });

        title.setOnMouseDragged(event -> {
            UI_FXML.currStage.setX(event.getScreenX() + xOffset);
            UI_FXML.currStage.setY(event.getScreenY() + yOffset);
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
                setText();
                UI_FXML.currStage.show();
            } catch(IOException e){}
        });

    }


    public void setText(){
        this.score1.setText("");
        this.score2.setText("");
        this.score3.setText("");
        this.score4.setText("");
        this.score5.setText("");
    }
}

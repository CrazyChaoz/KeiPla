package GUI_source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;



public class Titlebar_Functionality{
    private static double xOffset;
    private static double yOffset;

    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button title;
    @FXML
    private Button back;

    public void Titlebar_Functionality(Titlebar_Functionality i){
        i.title.setOnMousePressed(event -> {
            xOffset = UI_FXML.currStage.getX() - event.getScreenX();
            yOffset = UI_FXML.currStage.getY() - event.getScreenY();
        });

        i.title.setOnMouseDragged(event -> {
            UI_FXML.currStage.setX(event.getScreenX() + xOffset);
            UI_FXML.currStage.setY(event.getScreenY() + yOffset);
        });

        i.minimize.setOnAction(event -> {
            UI_FXML.currStage.setIconified(true);
        });

        i.close.setOnAction(event -> {
            UI_FXML.currStage.close();
        });

        i.back.setOnAction(event -> {
            try {
                UI_FXML.currStage.close();
                UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                UI_FXML.currStage.setTitle("Hauptmenü");
                Scene s=(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
                s.setFill(Color.TRANSPARENT);
                UI_FXML.currStage.setScene(s);
                UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                UI_FXML.currStage.show();
            } catch(IOException e){}
        });
    }
}

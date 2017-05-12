package GUI_source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller_Multi_End extends Titlebar_Functionality implements Initializable {

    @FXML
    private Label score;
    @FXML
    private Label result;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        setText();
        Titlebar_Functionality(this);
    }


    public void setText(){

        this.result.setText(UI_FXML.multi_result);
        this.score.setText("Score: "+UI_FXML.score);
        UI_FXML.score=0;
        UI_FXML.multiplayer=0;
        UI_FXML.multi_result=null;
    }

}

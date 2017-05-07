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


public class Controller_ChooseNetMode extends Titlebar_Functionality implements Initializable {
    private int selected;

    @FXML
    private Button host;
    @FXML
    private Button connect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        this.host.setOnAction(event -> {
            host.setId("radioCh");
            connect.setId("radio");
            System.out.println("Clicked on \"host\"");
        });
        this.connect.setOnAction(event -> {
            host.setId("radio");
            connect.setId("radioCh");
            System.out.println("Clicked on \"connect\"");
        });
        Titlebar_Functionality(this);
    }
}

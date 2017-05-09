package GUI_source;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;


public class Controller_ChooseNetMode extends Titlebar_Functionality implements Initializable {
    private int selected;

    @FXML
    private Button host;
    @FXML
    private Button connect;
    @FXML
    private Label myip;
    @FXML
    private Button startgame;
    @FXML
    private TextField othersip;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            myip.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        this.host.setOnAction(event -> {
            selected=4;
            host.setId("radioCh");
            connect.setId("radio");
            System.out.println("Clicked on \"host\"");
        });
        this.connect.setOnAction(event -> {
            selected=3;
            host.setId("radio");
            connect.setId("radioCh");
            System.out.println("Clicked on \"connect\"");
        });


        this.startgame.setOnAction(event -> {

            if(selected==3) {
                System.out.println("wörks");
                try {
                    new Multiplayer_Client(InetAddress.getByName(this.othersip.getText()), 63956).start();
                } catch (UnknownHostException e) {
                    this.othersip.setText("Not an IP");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }else if(selected==4){
                System.out.println("wörks serveerere");
                new Multiplayer_Server(63956).start();
            }
        });

        Titlebar_Functionality(this);
    }
}

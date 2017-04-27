package GUI_source;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button singleplayer;
    @FXML
    private Button startgame;
    @FXML
    private Button question;
    @FXML
    private Button answer1;
    @FXML
    private Button singleplayer;
    @FXML
    private Button startgame;



    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.singleplayer.setOnAction(event -> {
            try {
                UI_FXML.currStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.minimize.setOnAction(event -> {
            UI_FXML.currStage.setIconified(true);
        });
        this.close.setOnAction(event -> {
            UI_FXML.currStage.close();
        });
        this.startgame.setOnAction(event -> {

            UI_FXML.currStage.close();
        });
    }
}

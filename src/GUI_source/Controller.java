package GUI_source;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button singleplayer;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.singleplayer.setOnAction(event -> {

        });
        this.minimize.setOnAction(event -> {

        });
        this.close.setOnAction(event -> {

        });
    }
}

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
    private static String selected=null;
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
    private Button answer2;
    @FXML
    private Button answer3;
    @FXML
    private Button answer4;


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
            Logic.randomFilePicker();
            this.question.setText(UI_FXML.currQuestion[0]);
            this.answer1.setText(UI_FXML.currQuestion[1]);
            this.answer2.setText(UI_FXML.currQuestion[2]);
            this.answer3.setText(UI_FXML.currQuestion[3]);
            this.answer4.setText(UI_FXML.currQuestion[4]);
        });
        this.answer1.setOnAction(event -> {
            selected=UI_FXML.currQuestion[1];
        });
        this.answer2.setOnAction(event -> {
            selected=UI_FXML.currQuestion[2];
        });
        this.answer3.setOnAction(event -> {
            selected=UI_FXML.currQuestion[3];
        });
        this.answer4.setOnAction(event -> {
            selected=UI_FXML.currQuestion[4];
        });
    }
}

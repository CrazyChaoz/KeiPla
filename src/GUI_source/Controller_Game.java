package GUI_source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_Game implements Initializable {
    private static String selected=null;
    private static int hardness;

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
    @FXML
    private Button submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


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

        this.submit.setOnAction(event -> {
            if(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])].equals(selected)){
                UI_FXML.score++;
                newQuestion();
            }else{
                try {
                    UI_FXML.currStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void newQuestion(){
        try {
            UI_FXML.currQuestion=Logic.randomFilePicker(hardness);
        }catch (Exception e){

            this.question.setText(UI_FXML.currQuestion[0]);
            this.answer1.setText(UI_FXML.currQuestion[1]);
            this.answer2.setText(UI_FXML.currQuestion[2]);
            this.answer3.setText(UI_FXML.currQuestion[3]);
            this.answer4.setText(UI_FXML.currQuestion[4]);
        }

    }
}
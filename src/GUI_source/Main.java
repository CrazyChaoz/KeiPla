package GUI_source;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static GUI_source.UI_FXML.running;


//Main Class
public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception{
        LoginScreen.start_LoginForm();
        running=0;
    }


    public static void main(String[] args) {
        try {
            new Controller_Options().loadConf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(running==0)
            launch(args);
    }
}

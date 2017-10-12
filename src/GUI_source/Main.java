package GUI_source;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static GUI_source.UI_FXML.running;

public class Main extends Application {
    public static Controller_Options conf;
    @Override
    public void start(Stage stage) throws Exception{
        LoginScreen.start_LoginForm();
        running=0;
    }

    public Controller_Options getConf() {
        return conf;
    }

    public static void main(String[] args) {
        conf = new Controller_Options();
        try {
            conf.loadConf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(running==0)
            launch(args);
    }
}
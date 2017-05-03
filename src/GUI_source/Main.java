package GUI_source;

import javafx.application.Application;
import javafx.stage.Stage;

import static GUI_source.UI_FXML.running;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        UI_FXML ui=new UI_FXML();
        ui.start_LoginForm();
        running=1;
    }


    public static void main(String[] args) {
        if(running==0)
            launch(args);
    }
}

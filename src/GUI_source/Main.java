package GUI_source;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        UI_FXML ui=new UI_FXML();
        ui.start_LoginForm();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

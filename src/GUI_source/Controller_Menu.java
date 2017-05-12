package GUI_source;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Menu implements Initializable {
    private static double xOffset;
    private static double yOffset;

    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button singleplayer;
    @FXML
    private Button multiplayer;
    @FXML
    private Button options;
    @FXML
    private Button title;


    @Override
    public void initialize(URL location, ResourceBundle resources){


        this.singleplayer.setOnAction(event -> {
            try {
                UI_FXML.currStage.setTitle("Singleplayer");
                Scene s=new Scene(FXMLLoader.load(getClass().getResource("ChooseMode.fxml")));
                s.getStylesheets().addAll(UI_FXML.class.getResource(UI_FXML.theme).toExternalForm());
                s.setFill(Color.TRANSPARENT);
                UI_FXML.currStage.setScene(s);
                System.out.println("Clicked on \"singleplayer\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.multiplayer.setOnAction(event -> {
            try {
                UI_FXML.currStage.setTitle("Netzwerkmodus");
                Scene s=new Scene(FXMLLoader.load(getClass().getResource("ChooseNetMode.fxml")));
                s.getStylesheets().addAll(UI_FXML.class.getResource(UI_FXML.theme).toExternalForm());
                s.setFill(Color.TRANSPARENT);
                UI_FXML.currStage.setScene(s);
                System.out.println("Clicked on \"multiplayer\"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.options.setOnAction(event -> {
            try {
                UI_FXML.currStage.setTitle("Optionen");
                Scene s=new Scene(FXMLLoader.load(getClass().getResource("Options.fxml")));
                s.getStylesheets().addAll(UI_FXML.class.getResource(UI_FXML.theme).toExternalForm());
                s.setFill(Color.TRANSPARENT);
                UI_FXML.currStage.setScene(s);
                System.out.println("Clicked on \"options\"");
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


        title.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = UI_FXML.currStage.getX() - event.getScreenX();
                yOffset = UI_FXML.currStage.getY() - event.getScreenY();
            }
        });

        title.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UI_FXML.currStage.setX(event.getScreenX() + xOffset);
                UI_FXML.currStage.setY(event.getScreenY() + yOffset);
            }
        });
    }
}

package GUI_source;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

/**
 * Created by testuser on 27.04.2017.
 */
public class UI_FXML {
    public static String NAME=null;
    public static Stage currStage=null;
    public static int score=0;
    public static int hardness=0;
    public static String[] currQuestion=new String[6];
    //##########################################
    //##########################################
    //##########################################


    public void start_LoginForm() throws Exception {
        Stage stage=new Stage();
        currStage=stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Login");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(60);
        grid.setVgap(5);
        grid.setId("bg_Login");

        grid.setPadding(new Insets(25, 25, 25, 25));

        TextField userTextField = new TextField();
        userTextField.setPromptText("Username");
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Anmelden");
        btn.setId("small-button");
        btn.setMinSize(160,40);
        HBox hbBtn = new HBox();

        hbBtn.getChildren().add(btn);
        hbBtn.setAlignment(Pos.TOP_RIGHT);
        grid.add(hbBtn, 1, 3);

        btn.setOnAction(e -> {

            NAME=userTextField.getText();
            if(NAME!=null&&!NAME.equals("")){
                stage.close();
                currStage=null;
                try {
                    start_MainMenu();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(grid, 800, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());


        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.setScene(scene);
        stage.show();
    }
    //##########################################
    //##########################################
    //##########################################
    public void start_MainMenu() throws Exception {
        Stage stage=new Stage();
        currStage=stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Menu");



        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml")));
        scene.setFill(Color.TRANSPARENT);

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        UI_FXML ui=new UI_FXML();
        ui.start_LoginForm();
    }
}

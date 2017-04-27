package GUI_source;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class UI {
    public String NAME=null;
    //##########################################
    //##########################################
    //##########################################

    public void start_LoginForm()  {
        Stage stage=new Stage();
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

        /*
        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Password");
        grid.add(pwBox, 1, 2);*/

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
                start_MainMenu();
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
    public void startWebView(Stage webStage) {

        StackPane root = new StackPane();
        root.setId("background");
        WebView myWebView = new WebView();
        WebEngine engine = myWebView.getEngine();
        engine.loadContent("<h1>This is just a test!</h1>");

        root.getChildren().add(myWebView);

        Scene scene = new Scene(root, 1280, 720);

        webStage.setTitle("KeiPla");
        webStage.getIcons().add(new Image("file:KeiPla-Icon.png"));
        webStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        webStage.setScene(scene);
        webStage.show();
    }
    //##########################################
    //##########################################
    //##########################################
    public void start_MainMenu()  {
        Stage stage=new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Menu");
        stage.setWidth(720);
        stage.setHeight(448);

        GridPane grid = new GridPane();
        grid.setHgap(60);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button btn1 = new Button("Singleplayer");
        btn1.setMinSize(200,50);
        btn1.setId("std-button");
        Button btn2 = new Button("Multiplayer");
        btn2.setMinSize(200,50);
        btn2.setId("std-button");
        Button btn3 = new Button("Optionen");
        btn3.setMinSize(200,50);
        btn3.setId("std-button");

        grid.add(btn1, 1, 1);
        grid.add(btn2, 1, 2);
        grid.add(btn3, 1, 3);

        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.setId("background");
        box.getChildren().add(grid);
        box.getChildren().add(getUpperRight(stage));


        btn1.setOnAction(e -> {
            start_chooseMode();
            stage.close();
        });
        btn2.setOnAction(e -> {

        });
        btn3.setOnAction(e -> {

        });

        Scene scene = new Scene(box, 800, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.setScene(scene);
        stage.show();
    }
    //#####***##################################
    //#######*##################################
    //#######***################################
    public void start_chooseMode(){
        Stage stage=new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Menu");
        stage.setWidth(720);
        stage.setHeight(448);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(60);
        grid.setVgap(5);
        grid.setId("background");


        grid.setPadding(new Insets(25, 25, 25, 25));

        Button btn1 = new Button();
        btn1.setMinSize(48,48);
        btn1.setId("leicht");
        Label lbl1=new Label("Leicht");
        Button btn2 = new Button();
        btn2.setMinSize(48,48);
        btn2.setId("mittel");
        Label lbl2=new Label("Mittel");
        Button btn3 = new Button();
        btn3.setMinSize(48,48);
        btn3.setId("schwer");
        Label lbl3=new Label("Schwer");

        lbl1.setId("difficulty");
        lbl2.setId("difficulty");
        lbl3.setId("difficulty");

        grid.add(lbl1, 1, 1);
        grid.add(lbl2, 2, 1);
        grid.add(lbl3, 3, 1);
        grid.add(btn1, 1, 2);
        grid.add(btn2, 2, 2);
        grid.add(btn3, 3, 2);

        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.setId("background");
        box.getChildren().add(grid);
        box.getChildren().add(getUpperRight(stage));

        btn1.setOnAction(e -> {

        });
        btn2.setOnAction(e -> {

        });
        btn1.setOnAction(e -> {

        });

        Scene scene = new Scene(box, 700, 450);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.setScene(scene);

        stage.show();
    }

    public HBox getUpperRight(Stage stage){

        //####################################
        HBox box1=new HBox();
        box1.setAlignment(Pos.TOP_RIGHT);
        box1.setSpacing(10);
        //####################################

        Button minimize=new Button();
        minimize.setId("minimize");
        box1.getChildren().add(minimize);
        minimize.setOnAction(e -> stage.setIconified(true));

        //####################################

        Button close=new Button();
        close.setId("close");
        box1.getChildren().add(close);
        box1.setId("buttonarea");
        close.setOnAction(e -> Platform.exit());

        //####################################
        return box1;
    }

    public HBox getReturnButton(Stage stage){

        //####################################
        HBox box1=new HBox();
        box1.setAlignment(Pos.TOP_RIGHT);
        box1.setSpacing(10);
        //####################################

        Button ret=new Button();
        ret.setId("back");
        box1.getChildren().add(ret);
        ret.setOnAction(e -> {
            stage.close();
            start_MainMenu();
        });

        //####################################
        return box1;
    }
}

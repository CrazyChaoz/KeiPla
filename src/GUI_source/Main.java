package GUI_source;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        start_LoginForm();
    }
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
        HBox hbBtn = new HBox();

        hbBtn.getChildren().add(btn);
        hbBtn.setAlignment(Pos.TOP_RIGHT);
        grid.add(hbBtn, 1, 3);

        btn.setOnAction(e -> {
            System.out.println("Button pressed");
            stage.close();
            start_chooseMode();
        });

        Scene scene = new Scene(grid, 800, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        stage.getIcons().add(new Image("file:KeiPla-Icon-32.png"));
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


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(60);
        grid.setVgap(5);
        grid.setId("background");

        grid.setPadding(new Insets(25, 25, 25, 25));

        Button btn1 = new Button("Singleplayer");
        Button btn2 = new Button("Multiplayer");
        Button btn3 = new Button("Options");


        grid.add(btn1, 1, 1);
        grid.add(btn2, 1, 2);
        grid.add(btn3, 1, 3);

        btn1.setOnAction(e -> {

        });
        btn2.setOnAction(e -> {

        });
        btn1.setOnAction(e -> {

        });

        Scene scene = new Scene(grid, 800, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        stage.getIcons().add(new Image("file:KeiPla-Icon-128.png"));
        stage.setScene(scene);
        stage.show();
    }
    //##########################################
    //##########################################
    //##########################################
    public void start_chooseMode(){
        Stage stage=new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Menu");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(60);
        grid.setVgap(5);
        grid.setId("background");


        grid.setPadding(new Insets(25, 25, 25, 25));

        Button btn1 = new Button("");
        Label lbl1=new Label("Leicht");
        Button btn2 = new Button("");
        Label lbl2=new Label("Leicht");
        Button btn3 = new Button("");
        Label lbl3=new Label("Leicht");


        grid.add(lbl1, 1, 1);
        grid.add(lbl2, 2, 1);
        grid.add(lbl3, 3, 1);
        grid.add(btn1, 1, 2);
        grid.add(btn2, 2, 2);
        grid.add(btn3, 3, 2);

        //##########
        //right upper buttons
        GridPane grdPn=new GridPane();
        Button close=new Button();
        close.setId("close");
        grdPn.add(close,2,1);
        Button minimize=new Button();
        minimize.setId("minimize");
        grdPn.add(minimize,1,1);
        grdPn.setAlignment(Pos.TOP_RIGHT);

        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.setId("background");
        box.getChildren().add(grid);
        box.getChildren().add(grdPn);


        btn1.setOnAction(e -> {

        });
        btn2.setOnAction(e -> {

        });
        btn1.setOnAction(e -> {

        });

        Scene scene = new Scene(box, 800, 450);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        stage.getIcons().add(new Image("file:KeiPla-Icon-128.png"));
        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

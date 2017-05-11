package GUI_source;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class LoginScreen {
    public static void start_LoginForm() throws Exception {
        Stage stage=new Stage();
        UI_FXML.currStage=stage;
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
        //Für die Länge des Eingabefeldes, Maxlength=15
        userTextField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (userTextField.getText().length() >= 15) {
                    userTextField.setText(userTextField.getText().substring(0, 15));
                }
            }
        });
        grid.add(userTextField, 1, 1);

        Button btn = new Button("Anmelden");
        btn.setId("small-button");
        btn.setMinSize(160,40);
        HBox hbBtn = new HBox();

        hbBtn.getChildren().add(btn);
        hbBtn.setAlignment(Pos.TOP_RIGHT);
        grid.add(hbBtn, 1, 3);


        /*
          ######### When Button "Anmelden" is clicked #########
          ######### or ENTER is pressed -> doLogIn(); #########
         */
        btn.setOnAction(e -> {
            doLogIn(userTextField, stage);
        });
        userTextField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER:
                    doLogIn(userTextField, stage); break;
            }
        });

        Scene scene = new Scene(grid, 800, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(UI_FXML.class.getResource(UI_FXML.theme).toExternalForm());


        stage.getIcons().add(new Image(UI_FXML.class.getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
        stage.setScene(scene);
        stage.show();
    }
    public static void doLogIn(TextField userTextField, Stage stage){
        System.out.println("Clicked on \"anmelden\"");
        UI_FXML.NAME=userTextField.getText();
        if(UI_FXML.NAME!=null&&!UI_FXML.NAME.equals("")){
            stage.close();
            UI_FXML.currStage=null;
            try {
                UI_FXML.start_MainMenu();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

package GUI_source;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        start_LoginForm();
    }

    public void start_LoginForm()  {
        Stage stage=new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Login");


        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(60);
        grid.setVgap(5);
        grid.setId("bg1");

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

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Button pressed");
            }
        });

        Scene scene = new Scene(grid, 800, 450);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        stage.getIcons().add(new Image("file:test_java.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

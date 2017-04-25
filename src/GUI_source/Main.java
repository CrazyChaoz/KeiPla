package GUI_source;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        /*
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Hello Tester");

        Group root=new Group();

        HBox box =new HBox();
        //box.getChildren().add(img);
        //root.getChildren().add(box);


        Scene scene=new Scene(root);


        stage.setScene(scene);
        stage.show();*/
        start_LoginForm();
    }

    public void start_LoginForm() {
        Stage stage=new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Login");

        ImageView img=new ImageView(new Image(""));

        Group root=new Group();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        String image =  Main.class.getResource("test_java.png").toExternalForm();
        grid.setStyle(  "-fx-background-color: rgba(0, 100, 100, 0);\n" +
                "    -fx-background-radius: 10;    \n" +
                "    -fx-background-image: url('"+image+"');");
        grid.setPadding(new Insets(25, 25, 25, 25));

        TextField userTextField = new TextField();
        userTextField.setPromptText("Username");
        grid.add(userTextField, 1, 1);


        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("Password");
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Anmelden");
        HBox hbBtn = new HBox(10);

        HBox box =new HBox();
        box.getChildren().add(img);

        //hbBtn.getChildren().add(img);
        //hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();

        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                System.out.println("Button pressed");
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

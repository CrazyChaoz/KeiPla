package GUI_source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Hello Tester");

        Group root=new Group();

        ImageView img=new ImageView(new Image("file:test_java.png"));

        HBox box =new HBox();
        box.getChildren().add(img);

        root.getChildren().add(box);


        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
/*
Some stuff PENIS
 */

    public static void main(String[] args) {
        //comment
        launch(args);
    }
}

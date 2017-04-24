package GUI_source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Group root=new Group();

        ImageView img=new ImageView();
        img.setImage(new Image("file:test_java.png"));
        HBox box =new HBox();
        box.getChildren().add(img);
        root.getChildren().add(box);
        primaryStage.setTitle("Hello Tester");
        primaryStage.setScene(new Scene(root));
        Scene scene=
    primaryStage.setF
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

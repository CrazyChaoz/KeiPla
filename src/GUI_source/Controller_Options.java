package GUI_source;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_Options extends Titlebar_Functionality implements Initializable {
    public static int selected=0;
    public static Current_Settings cur;
    @FXML
    private Button normal;
    @FXML
    private Button dark;
    @FXML
    private Button dark_inverted;
    @FXML
    private Button submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.normal.setOnAction(event -> {
            selected=0;
            normal.setId("radioCh");
            dark.setId("radio");
            dark_inverted.setId("radio");
            System.out.println("Clicked on \"normal\"");
        });
        this.dark.setOnAction(event -> {
            selected=1;
            dark.setId("radioCh");
            normal.setId("radio");
            dark_inverted.setId("radio");
            System.out.println("Clicked on \"dark\"");
        });
        this.dark_inverted.setOnAction(event -> {
            selected=2;
            dark_inverted.setId("radioCh");
            dark.setId("radio");
            normal.setId("radio");
            System.out.println("Clicked on \"dark_inverted\"");
        });
        this.submit.setOnAction((ActionEvent event) -> {
            try {
                writeConf();
            } catch (IOException e) {
                e.printStackTrace();
            }
            UI_FXML.setTheme();
            goBack();
        });
        Titlebar_Functionality(this);
    }

    public void loadConf() throws IOException {
        /*
         *  info:
         *      0-2 -> normal;dark;dark_inverted; (Style)
         */
        File config = new File("res" + File.separator + "preferences.dat");

        //Later we will cut it by \r\n

        FileReader input = new FileReader(config);
        int style = input.read();
        System.out.println("What stylenumber: "+style);
        cur = new Current_Settings(style);

        switch (cur.getStyle()) {
            case 0:
                System.out.println("Style: "+0);
                break;
            case 1:
                System.out.println("Style: "+1);
                break;
            case 2:
                System.out.println("Style: "+2);
                break;
            default:
                System.out.println("default");
                break;
        }
    }
    public static void writeConf() throws IOException {
        /*
         *  info:
         *      0-2 -> normal;dark;dark_inverted; (Style)
         */
        File config = new File("res" + File.separator + "preferences.dat");
        //Later we will cut it by \r\n
        FileWriter input = new FileWriter(config);
        input.write(selected);
        try {
            cur.setAll(selected);
        }catch (NullPointerException e) { e.printStackTrace(); }

        System.out.println(selected);
        input.close();
    }
}
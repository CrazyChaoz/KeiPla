package GUI_source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller_Highscore implements Initializable {
    private static double xOffset;
    private static double yOffset;

    @FXML
    private Label score1;
    @FXML
    private Label score2;
    @FXML
    private Label score3;
    @FXML
    private Label score4;
    @FXML
    private Label score5;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Button title;
    @FXML
    private Button back;


    @Override
    public void initialize(URL location, ResourceBundle resources){
        setText();

        title.setOnMousePressed(event -> {
            xOffset = UI_FXML.currStage.getX() - event.getScreenX();
            yOffset = UI_FXML.currStage.getY() - event.getScreenY();
        });

        title.setOnMouseDragged(event -> {
            UI_FXML.currStage.setX(event.getScreenX() + xOffset);
            UI_FXML.currStage.setY(event.getScreenY() + yOffset);
        });

        this.minimize.setOnAction(event -> {
            UI_FXML.currStage.setIconified(true);
        });

        this.close.setOnAction(event -> {
            UI_FXML.currStage.close();
        });

        this.back.setOnAction(event -> {
            try {
                UI_FXML.currStage.close();
                UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                UI_FXML.currStage.setTitle("Hauptmenü");
                UI_FXML.currStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenu.fxml"))));
                UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                setText();
                UI_FXML.currStage.show();
            } catch(IOException e){}
        });

    }


    public void setText(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        List list=new ArrayList<String[]>();
        String[] s=new String[2];

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse("res"+ File.separator+"Highscore.xml");
            for(int i=0;;i++) {
                s[0]=document.getElementsByTagName("name").item(i).getTextContent();
                s[1]=document.getElementsByTagName("score").item(i).getTextContent();
                list.add(s);
            }
        }catch(SAXException e){

        }catch(IOException e){

        }catch(Exception e){
            System.err.println("Exception"+e.getMessage());
        }
        Collections.sort(list, new Comparator<String[]>() {

            @Override
            public int compare(String[] o1, String[] o2){
                if(!o1[1].equals(o2[1])){
                    return Integer.parseInt(o1[1])-Integer.parseInt(o2[1]);
                }
                return o1[0].compareTo(o2[0]);
            }
        });

        this.score1.setText(((String[])list.get(0))[1]);
        this.score2.setText(((String[])list.get(1))[1]);
        this.score3.setText(((String[])list.get(2))[1]);
        this.score4.setText(((String[])list.get(3))[1]);
        this.score5.setText(((String[])list.get(4))[1]);
    }

    public static void Test_setText(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        List<String[]> list=new ArrayList<String[]>();
        String[] s=new String[2];

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse("res"+ File.separator+"Highscore.xml");
            int i=0;
            while(true){
                s[0]=document.getElementsByTagName("name").item(i).getTextContent();
                s[1]=document.getElementsByTagName("score").item(i).getTextContent();
                System.out.println(s[0]+s[1]);
                System.out.println("##--##--##");
                list.add(s);
                i++;
            }
        }catch(SAXException e){
            System.err.println("SAX");
        }catch(IOException e){
            System.err.println("IOEX");
        }catch(Exception e){
            System.err.println("Exception: "+e.getMessage());
        }
        Collections.sort(list, new Comparator<String[]>() {

            @Override
            public int compare(String[] o1, String[] o2){
                if(!o1[1].equals(o2[1])){
                    return Integer.parseInt(o1[1])-Integer.parseInt(o2[1]);
                }
                return o1[0].compareTo(o2[0]);
            }
        });

        for(String[] st:list)
            System.out.println(st[0]+st[1]);

    }

    public static void main(String[] args) {
        Controller_Highscore.Test_setText();
    }
}

package GUI_source;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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


public class Controller_Highscore extends Titlebar_Functionality implements Initializable {


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


    @Override
    public void initialize(URL location, ResourceBundle resources){
        setText();
        Titlebar_Functionality(this);
    }


    public void setText(){
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
                list.add(new String[]{s[0], s[1]});
                i++;
            }
        }catch(SAXException e){
            System.err.println("SAXException");
        }catch(IOException e){
            System.err.println("IOEX");
        }catch(Exception e){
            System.err.println("Exception: "+e.getMessage());
        }
        Collections.sort(list, new Comparator<String[]>() {

            @Override
            public int compare(String[] o1, String[] o2){
                if(!o1[1].equals(o2[1])){
                    return Integer.parseInt(o2[1])-Integer.parseInt(o1[1]);
                }
                return o1[0].compareTo(o2[0]);
            }
        });

        this.score1.setText("--");
        this.score2.setText("--");
        this.score3.setText("--");
        this.score4.setText("--");
        this.score5.setText("--");

        try{
        this.score1.setText((list.get(0))[0]+": "+(list.get(0))[1]);
        this.score2.setText((list.get(1))[0]+": "+(list.get(1))[1]);
        this.score3.setText((list.get(2))[0]+": "+(list.get(2))[1]);
        this.score4.setText((list.get(3))[0]+": "+(list.get(3))[1]);
        this.score5.setText((list.get(4))[0]+": "+(list.get(4))[1]);
        }catch(Exception e){}
    }

}

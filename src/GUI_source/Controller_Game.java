package GUI_source;

import javafx.application.Platform;
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
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_Game implements Initializable {
    private static String selected=null;

    @FXML
    private Label question;
    @FXML
    private Button answer1;
    @FXML
    private Button answer2;
    @FXML
    private Button answer3;
    @FXML
    private Button answer4;
    @FXML
    private Button surrender;
    @FXML
    private Button submit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setText();

        this.answer1.setOnAction(event -> {
            answer1.setId("answerCh");
            answer2.setId("answer");
            answer3.setId("answer");
            answer4.setId("answer");
            selected=UI_FXML.currQuestion[1];
            System.out.println("Clicked on \"answer1\" -> "+selected);
        });

        this.answer2.setOnAction(event -> {
            answer1.setId("answer");
            answer2.setId("answerCh");
            answer3.setId("answer");
            answer4.setId("answer");
            selected=UI_FXML.currQuestion[2];
            System.out.println("Clicked on \"answer2\" -> "+selected);
        });

        this.answer3.setOnAction(event -> {
            answer1.setId("answer");
            answer2.setId("answer");
            answer3.setId("answerCh");
            answer4.setId("answer");
            selected=UI_FXML.currQuestion[3];
            System.out.println("Clicked on \"answer3\" -> "+selected);
        });

        this.answer4.setOnAction(event -> {
            answer1.setId("answer");
            answer2.setId("answer");
            answer3.setId("answer");
            answer4.setId("answerCh");
            selected=UI_FXML.currQuestion[4];
            System.out.println("Clicked on \"answer4\" -> "+selected);
        });

        this.submit.setOnAction(event -> {
            System.out.println("Clicked on \"submit\"");


            if(UI_FXML.multiplayer==0)
                if(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])].equals(selected)){
                    UI_FXML.score++;
                    try {
                        System.out.println("Clicked on \"submit\"");
                        new Question(UI_FXML.hardness);
                        UI_FXML.currStage.close();
                        UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
                        UI_FXML.currStage.setTitle("DAS SPIEL");
                        Scene s=new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml")));
                        s.setFill(Color.TRANSPARENT);
                        UI_FXML.currStage.setScene(s);
                        UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                        setText();
                        UI_FXML.currStage.show();
                    } catch(IOException e){}
                }else{
                    rip();
                }
            else{
                UI_FXML.lock = 0;
                UI_FXML.multi_result=selected;

            }

            });

        this.surrender.setOnAction(event -> {
            if(UI_FXML.multiplayer==0)
            System.out.println("Clicked on \"surrender\"");
            rip();
        });

    }

    public void rip(){
        try {
            addHighscore();
            UI_FXML.currStage.close();
            UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
            UI_FXML.currStage.setTitle("Highscore");
            Scene s=new Scene(FXMLLoader.load(getClass().getResource("Highscore.fxml")));
            s.setFill(Color.TRANSPARENT);
            UI_FXML.currStage.setScene(s);
            UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
            UI_FXML.currStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addHighscore() {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse("res" + File.separator + "Highscore.xml");

            Element root=document.getDocumentElement();
            Element name=document.createElement("name");
            Element score=document.createElement("score");

            name.appendChild(document.createTextNode(UI_FXML.NAME));
            score.appendChild(document.createTextNode(UI_FXML.score+""));
            UI_FXML.score=0;
            root.appendChild(name);
            root.appendChild(score);

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult("res" + File.separator + "Highscore.xml");
            transformer.transform(source, result);

        }catch (Exception e){}


    }

    public void setText(){
        this.question.setText(UI_FXML.currQuestion[0]);
        this.answer1.setText(UI_FXML.currQuestion[1]);
        this.answer2.setText(UI_FXML.currQuestion[2]);
        this.answer3.setText(UI_FXML.currQuestion[3]);
        this.answer4.setText(UI_FXML.currQuestion[4]);
    }
}

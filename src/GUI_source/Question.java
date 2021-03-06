package GUI_source;

/*
    Directory-Structure
    /
    |-Thema_1
    |   |-1 -> leicht (ez)
    |   |-2 -> mittel (mid)
    |   |-3 -> schwer (hard)
    |-Thema_2
    |   |-1 -> leicht (ez)
    |   |-2 -> mittel (mid)
    |   |-3 -> schwer (hard)
    |-...
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Random;
import org.w3c.dom.Document;

public class Question {
    private int wh=1;

    public Question(int hardness){
        Random rndm=new Random();
        File file;
        String path;
        while(wh==1){
            wh=0;
            switch(hardness){
                case 1:
                    path="res"+ File.separator+"questions"+ File.separator+"ez"+File.separator+(rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"ez")))+1)+".xml";
                    System.out.println(path);
                    file=new File(path);
                    getXMLInhalt(file);
                    break;
                case 2:
                    path="res"+ File.separator+"questions"+ File.separator+"mid"+File.separator+(rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"mid")))+1)+".xml";
                    System.out.println(path);
                    file=new File(path);
                    getXMLInhalt(file);
                    break;
                case 3:
                    path="res"+ File.separator+"questions"+ File.separator+"hard"+File.separator+(rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"hard")))+1)+".xml";
                    System.out.println(path);
                    file=new File(path);
                    getXMLInhalt(file);
                    break;

                default:
                    throw new RuntimeException("Wrong or no Hardness entered");
            }
        }
    }

    private void getXMLInhalt(File file){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
            UI_FXML.currQuestion=new String[6];
            UI_FXML.currQuestion[0] = document.getElementsByTagName("question").item(0).getTextContent();
            UI_FXML.currQuestion[1] = document.getElementsByTagName("answer").item(0).getTextContent();
            UI_FXML.currQuestion[2] = document.getElementsByTagName("answer").item(1).getTextContent();
            UI_FXML.currQuestion[3] = document.getElementsByTagName("answer").item(2).getTextContent();
            UI_FXML.currQuestion[4] = document.getElementsByTagName("answer").item(3).getTextContent();
            UI_FXML.currQuestion[5] = document.getElementsByTagName("right").item(0).getTextContent();
        }catch (Exception e){
            wh=1;
        }
    }

    public int getFileNumber(File folder){
        return folder.list().length;
    }/*
    public static void main(String[] args) {

        for (String s:Question.randomFilePicker(1)) {
            System.out.println(s);
        }

    }*/
}

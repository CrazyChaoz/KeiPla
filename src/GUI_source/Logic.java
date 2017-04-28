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

public class Logic {
    private static int wh=1;

    public static void randomFilePicker(int hardness){
        Random rndm=new Random();

        while(wh==1){
            wh=0;
            switch(hardness){
                case 1:
                    String path="res"+ File.separator+"questions"+ File.separator+"ez"+File.separator+(rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"ez")))+1)+".xml";
                    System.out.println(path);
                    File file=new File(path);
                    getXMLInhalt(file);
                    break;
                case 2:
                    getXMLInhalt(new File("res"+ File.separator+"questions"+ File.separator+"mid"+ File.separator+rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"mid"+ File.separator)))+1+".xml"));
                    break;
                case 3:
                    getXMLInhalt(new File("res"+ File.separator+"questions"+ File.separator+"hard"+ File.separator+rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"hard"+ File.separator)))+1+".xml"));
                    break;

                default:
                    throw new RuntimeException("Wrong or no Hardness entered");
            }
        }
    }

    public static void getXMLInhalt(File file){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
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

    public static int getFileNumber(File folder){
        return folder.list().length;
    }/*
    public static void main(String[] args) {

        for (String s:Logic.randomFilePicker(1)) {
            System.out.println(s);
        }

    }*/
}

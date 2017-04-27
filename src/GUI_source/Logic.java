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
    public static String[] randomFilePicker(int hardness){

        String[] retournage=null;
        Random rndm=new Random();

        switch(hardness){
            case 1:
                retournage=getXMLInhalt(new File("res"+ File.separator+"questions"+ File.separator+"ez"+File.separator+rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"ez"+File.separator)))+1+".xml"));
                break;
            case 2:
                retournage=getXMLInhalt(new File("res"+ File.separator+"questions"+ File.separator+"mid"+ File.separator+rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"mid"+ File.separator)))+1+".xml"));
                break;
            case 3:
                retournage=getXMLInhalt(new File("res"+ File.separator+"questions"+ File.separator+"hard"+ File.separator+rndm.nextInt(getFileNumber(new File("res"+ File.separator+"questions"+ File.separator+"hard"+ File.separator)))+1+".xml"));
                break;
        }
        return retournage;
    }

    public static String[] getXMLInhalt(File file){
        int wh=1;
        String[]retournage=new String[6];

        while(wh==1){
            wh=0;
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            Document document;
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                document = documentBuilder.parse(file);
                retournage[0] = document.getElementsByTagName("question").item(0).getTextContent();
                retournage[1] = document.getElementsByTagName("answer").item(0).getTextContent();
                retournage[2] = document.getElementsByTagName("answer").item(1).getTextContent();
                retournage[3] = document.getElementsByTagName("answer").item(2).getTextContent();
                retournage[4] = document.getElementsByTagName("answer").item(3).getTextContent();
                retournage[5] = document.getElementsByTagName("right").item(0).getTextContent();
            }catch (Exception e){
                e.printStackTrace();
                wh=1;
            }
        }
        return retournage;
    }

    public static int getFileNumber(File folder){
        return folder.list().length;
    }
    public static void main(String[] args) {

        for (String s:Logic.randomFilePicker(1)) {
            System.out.println(s);
        }

    }
}

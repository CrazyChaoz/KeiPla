package GUI_source;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Random;

/**
 * Created by testuser on 26.04.2017.
 */
public class Logic {
    public static void getQuestion(int hardness){
        switch(hardness){
            case 1:
                Random rndm=new Random();
                File file = new File("ez\\"+rndm.nextInt(6) + 1+".xml");
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                org.w3c.dom.Document document=null;
                try {
                    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    document = documentBuilder.parse(file);
                }catch (Exception e){}


                String usr = document.getElementsByTagName("user").item(0).getTextContent();
                String pwd = document.getElementsByTagName("password").item(0).getTextContent();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}

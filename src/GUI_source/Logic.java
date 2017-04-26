package GUI_source;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by testuser on 26.04.2017.
 */
public class Logic {
    public static String[] getQuestion(int hardness){

        String[] retournage=null;
        Random rndm=new Random();

        switch(hardness){
            case 1:
                getXMLInhalt(new File("res\\questions\\ez\\"+rndm.nextInt(getFileNumber(new File("res\\questions\\ez\\")))+1+".xml"));
                break;
            case 2:
                getXMLInhalt(new File("res\\questions\\mid\\"+rndm.nextInt(getFileNumber(new File("res\\questions\\mid\\")))+1+".xml"));
                break;
            case 3:
                getXMLInhalt(new File("res\\questions\\hard\\"+rndm.nextInt(getFileNumber(new File("res\\questions\\hard\\")))+1+".xml"));
                break;
        }


        return retournage;
    }

    public static String[] getXMLInhalt(File file){
        int wh=1;
        String[]retournage=new String[5];

        while(wh==1){
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            org.w3c.dom.Document document;
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                document = documentBuilder.parse(file);
                retournage[0] = document.getElementsByTagName("question").item(0).getTextContent();
                retournage[1] = document.getElementsByTagName("answer").item(0).getTextContent();
                retournage[2] = document.getElementsByTagName("answer").item(1).getTextContent();
                retournage[3] = document.getElementsByTagName("answer").item(2).getTextContent();
                retournage[4] = document.getElementsByTagName("answer").item(3).getTextContent();
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

        for (String s:Logic.getQuestion(1)) {
            System.out.println(s);
        }

    }
}

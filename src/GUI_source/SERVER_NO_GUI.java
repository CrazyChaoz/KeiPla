package GUI_source;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by kemp on 16.09.17.
 * KEIPLA SERVER
 */
public class SERVER_NO_GUI{
    public static void main(String[] args) {
        new SERVER_NO_GUI();
    }

    private int wh=1;
    private String multi_result=null;
    private int score=0;
    private String[] currQuestion=new String[6];

    public SERVER_NO_GUI() {
        int port=63956;
        List<BufferedReader> instreams=new ArrayList<>();
        List<PrintWriter> outstreams=new ArrayList<>();
        List<String> inputLines=new ArrayList<>();


        String version="1.0";


        System.out.println("Welcome to KeiPla_Server v"+version);
        System.out.println("How many Clients should connect?");
        int numClients=new Scanner(System.in).nextInt();

        try {
            System.out.println("This Machine: "+InetAddress.getLocalHost());
        }catch (UnknownHostException ignored){}

        System.out.println("Waiting for connections");

        try(
                ServerSocket serverSocket = new ServerSocket(port);

        ){
            int i=0;
            while(i<numClients) {
                Socket clientSocket = serverSocket.accept();

                outstreams.add(new PrintWriter(clientSocket.getOutputStream(), true));
                instreams.add(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
                i++;
            }

            String inputLine, outputLine=null;


            System.out.println("Host connected");
            newQ(1);
            outputLine=
                    currQuestion[0]+";"+
                            currQuestion[1]+";"+
                            currQuestion[2]+";"+
                            currQuestion[3]+";"+
                            currQuestion[4]+";"+
                            "1337"+"\n";
            for (PrintWriter out:outstreams)
                out.println(outputLine);



            System.out.println("waiting");

            for (PrintWriter out:outstreams)
                out.println("waiting");


            while (true) {
                for(BufferedReader in:instreams)
                    if((inputLine = in.readLine())==null)
                        break;

                System.out.println("Client: "+inputLine);
                System.out.println("Server: "+multi_result);

                Boolean otherSolution;
                Boolean ownSolution;

                if(!inputLine.equals("waiting")&&multi_result!=null){
                    otherSolution = inputLine.equals(currQuestion[Integer.parseInt(currQuestion[5])]);
                    ownSolution = multi_result.equals(currQuestion[Integer.parseInt(currQuestion[5])]);
                    multi_result=null;
                    if (otherSolution && ownSolution){
                        System.out.println("Both Right");
                        newQ(1);
                        outputLine =
                                currQuestion[0] + ";" +
                                        currQuestion[1] + ";" +
                                        currQuestion[2] + ";" +
                                        currQuestion[3] + ";" +
                                        currQuestion[4] + ";" +
                                        "1337" + "\n";
                        score++;
                    }else if (!otherSolution && ownSolution){
                        System.out.println("You Won");
                        multi_result = "You Won";
                        out.println("You Lost");
                        Thread.sleep(1200);
                        out.println("You Lost");
                        Thread.sleep(1200);
                        break;
                    }else if (otherSolution && !ownSolution){
                        System.out.println("You Lost");
                        multi_result = "You Lost";
                        out.println("You Won");
                        Thread.sleep(1200);
                        out.println("You Won");
                        Thread.sleep(1200);
                        break;
                    }else if(!otherSolution&&!ownSolution){
                        System.out.println("Both Wrong");
                        multi_result = "Both Lost";
                        out.println("Both Wrong");
                        Thread.sleep(1200);
                        out.println("Both Wrong");
                        Thread.sleep(1200);
                        break;
                    }else if (inputLine.equals("stop_communication")) {
                        break;
                    }
                }else{
                    System.out.println("waiting");
                    outputLine="waiting";
                }
                Thread.sleep(700);
                out.println(outputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "+port+" or listening for a connection");
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void newQ(int hardness){
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
            currQuestion=new String[6];
            currQuestion[0] = document.getElementsByTagName("question").item(0).getTextContent();
            currQuestion[1] = document.getElementsByTagName("answer").item(0).getTextContent();
            currQuestion[2] = document.getElementsByTagName("answer").item(1).getTextContent();
            currQuestion[3] = document.getElementsByTagName("answer").item(2).getTextContent();
            currQuestion[4] = document.getElementsByTagName("answer").item(3).getTextContent();
            currQuestion[5] = document.getElementsByTagName("right").item(0).getTextContent();
        }catch (Exception e){
            wh=1;
        }
    }

    public int getFileNumber(File folder){
        return folder.list().length;
    }
}

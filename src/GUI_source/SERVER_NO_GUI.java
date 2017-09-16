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
 * ENDLESS MODE ONLY
 * Protocol updated
 */
public class SERVER_NO_GUI{
    public static void main(String[] args) {
        new SERVER_NO_GUI();
    }

    private int wh=1;
    private String[] currQuestion=new String[6];

    public SERVER_NO_GUI() {
        int port=63956;

        List<Client> clients=new ArrayList<>();


        String version="1.1";


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
            while(clients.size()<numClients) {
                Socket clientSocket = serverSocket.accept();

                clients.add(
                    new Client(
                        new PrintWriter(clientSocket.getOutputStream(), true),
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                    )
                );
            }

            String outputLine=null;

            for(Client c:clients){
                String inpLn=c.in.readLine();
                if(inpLn==null)
                    break;
                else
                    c.name=inpLn;
            }

            System.out.println("Host connected");
            newQ(1);
            outputLine=
                    currQuestion[0]+";"+
                            currQuestion[1]+";"+
                            currQuestion[2]+";"+
                            currQuestion[3]+";"+
                            currQuestion[4]+";"+
                            "1337"+"\n";
            for (Client c:clients)
                c.out.println(outputLine);



            System.out.println("waiting");

            for (Client c:clients)
                c.out.println("waiting");


            while (true) {

                List<String> inpMsgs=new ArrayList<>();

                for(Client c:clients){
                    String inpLn=c.in.readLine();
                    if(inpLn==null)
                        break;
                    else
                        inpMsgs.add(inpLn);
                }

                for(String lastMsg:inpMsgs)
                    System.out.println("Client: "+lastMsg);

                System.out.println("Server: "+currQuestion[Integer.parseInt(currQuestion[5])]);



                //### stop communication###
                //### send score to all ###
                if(inpMsgs.contains("see_tat_shieeet")){
                    for (Client c:clients) {
                        c.out.println();
                    }
                    Thread.sleep(1200);
                    for (Client c:clients) {
                        c.out.println();
                    }
                    Thread.sleep(1200);
                    break;
                }


                if(!inpMsgs.contains("waiting")){

                    for(int i=0;i<clients.size();i++){
                        if(inpMsgs.get(i).equals(currQuestion[Integer.parseInt(currQuestion[5])]))
                            clients.get(i).score++;
                        System.out.println("Client: "+clients.get(i).name+"\nScore: "+clients.get(i).score);
                    }

                    newQ(1);
                    outputLine =
                            currQuestion[0] + ";" +
                            currQuestion[1] + ";" +
                            currQuestion[2] + ";" +
                            currQuestion[3] + ";" +
                            currQuestion[4] + ";" +
                            "1337" + "\n";

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

class Client{

    public PrintWriter out;
    public BufferedReader in;

    public String name;
    public int score;

    public Client(PrintWriter out, BufferedReader in) {
        this.out = out;
        this.in = in;
        score=0;
    }
}
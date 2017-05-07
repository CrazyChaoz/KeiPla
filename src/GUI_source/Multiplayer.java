package GUI_source;

import java.io.*;
import java.net.*;

public class Multiplayer{

    public String selected;

    public void startServerAction(int port){
        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){

            String inputLine, outputLine=null;
            System.out.println(InetAddress.getLocalHost());


            while ((inputLine = in.readLine()) != null){

                if(inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])&&selected.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])){
                    System.out.println("Both Right");
                    outputLine = UI_FXML.currQuestion[0]+";"+UI_FXML.currQuestion[1]+";"+UI_FXML.currQuestion[2]+";"+UI_FXML.currQuestion[3]+";"+
                            UI_FXML.currQuestion[4]+";"+UI_FXML.currQuestion[5]+";"+"1337"+"\n";
                }else if(!inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])&&selected.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])){
                    System.out.println("You Won");
                    UI_FXML.multi_result="You Won";
                    outputLine="You Lost\n";
                }else if(inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])&&!selected.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])){
                    System.out.println("You Lost");
                    UI_FXML.multi_result="You Lost";
                    outputLine="You Won\n";
                }else{
                    System.out.println("Both Wrong");
                    UI_FXML.multi_result="Both Lost";
                    outputLine="Both Wrong\n";
                }

                out.println(outputLine);

                if (inputLine.equals("stop_communication"))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "+port+" or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    public void startClientAction(InetAddress ip,int port){

        try (
                Socket socket = new Socket(ip, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        ) {
            UI_FXML.multiplayer=1;
            String fromServer;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("You Won")){
                    System.out.println("You Won");
                    UI_FXML.multi_result="You Won";
                }else if (fromServer.equals("You Lost")){
                    System.out.println("You Lost");
                    UI_FXML.multi_result="You Lost";
                }else if (fromServer.equals("Both Lost")){
                    System.out.println("Both Lost");
                    UI_FXML.multi_result="Both Lost";
                }else{
                    String[] s;
                    s=fromServer.split("\\n");
                    UI_FXML.currQuestion=s[0].split(";");
                }

                while(!UI_FXML.lock) {
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }

                if (selected != null) {
                    System.out.println("Client: " + selected);
                    out.println(selected+"\n");
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +port);
        }
    }

    public static void main(String[] args) {
        Multiplayer multiplayer=new Multiplayer();
        multiplayer.startServerAction(63956);
    }
}


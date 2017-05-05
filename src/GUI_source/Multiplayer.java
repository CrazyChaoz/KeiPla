package GUI_source;

import java.io.*;
import java.net.*;

/**
 * Created by testuser on 26.04.2017.
 */
public class Multiplayer{
    public static void startServerAction(int port){
        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){

            String inputLine, outputLine=null;

            while ((inputLine = in.readLine()) != null) {
                if(!inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[6])])){
                    System.out.println("YOU WON");
                    break;
                }

                outputLine = UI_FXML.currQuestion[0]+";"+UI_FXML.currQuestion[1]+";"+UI_FXML.currQuestion[2]+";"+UI_FXML.currQuestion[3]+";"+
                        UI_FXML.currQuestion[4]+";"+UI_FXML.currQuestion[5]+";"+"1337"+"\n";
                out.println(outputLine);
                if (inputLine.equals("stop_communication"))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "+port+" or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
    public static void startClientAction(InetAddress ip,int port){

        try (
                Socket socket = new Socket(ip, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        ) {
            UI_FXML.multiplayer=1;
            String fromServer;
            String fromClient=null;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("stop_communication"))
                    break;
                String[] s;
                s=fromServer.split("\\n");
                UI_FXML.currQuestion=s[0].split(";");

                if (fromClient != null) {
                    System.out.println("Client: " + fromClient);
                    out.println(fromClient);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +port);
        }
    }

}

//InetAddress.getLocalHost();

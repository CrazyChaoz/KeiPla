package GUI_source;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.*;

public class Multiplayer_Server extends Thread{

    public String selected=null;
    int port;

    public Multiplayer_Server(int port) {
        this.port = port;
    }


    public void run(){
        try {
            System.out.println(InetAddress.getLocalHost());
        }catch (UnknownHostException ignored){}
        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            UI_FXML.multiplayer=1;
            String inputLine, outputLine=null;
            System.out.println("Host connected");
            new Question(1);
            outputLine=
                    UI_FXML.currQuestion[0]+";"+
                    UI_FXML.currQuestion[1]+";"+
                    UI_FXML.currQuestion[2]+";"+
                    UI_FXML.currQuestion[3]+";"+
                    UI_FXML.currQuestion[4]+";"+
                    "1337"+"\n";
            out.println(outputLine);

            new Multiplayer_Game();

            System.out.println("reachable?");

            //initiate the waiting communication
            /*
            for (Thread thread:Thread.getAllStackTraces().keySet()) {
                System.out.println(thread.getName());
            }
            System.out.println(Thread.currentThread());*/

            out.println();
            while ((inputLine = in.readLine())!=null) {
                System.out.println("ClientMSG: " + inputLine);
                if (UI_FXML.lock == 0) {

                    Boolean otherSolution=inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])]);
                    Boolean ownSolution=UI_FXML.multi_result.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])]);

                    if (otherSolution && ownSolution){
                        //^^^^^^ Error lies here ^^^^^^^
                        System.out.println("Both Right");
                        outputLine =
                                UI_FXML.currQuestion[0] + ";" +
                                UI_FXML.currQuestion[1] + ";" +
                                UI_FXML.currQuestion[2] + ";" +
                                UI_FXML.currQuestion[3] + ";" +
                                UI_FXML.currQuestion[4] + ";" +
                                "1337" + "\n";
                        new Multiplayer_Game();
                    }else if (!otherSolution && ownSolution){
                        System.out.println("You Won");
                        UI_FXML.multi_result = "You Won";
                        outputLine = "You Lost\n";
                        new Multi_End();
                    }else if (otherSolution&&!ownSolution){
                        System.out.println("You Lost");
                        UI_FXML.multi_result = "You Lost";
                        outputLine = "You Won\n";
                        new Multi_End();
                    }else if (inputLine.equals("stop_communication"))
                        break;
                    else{
                        System.out.println("Both Wrong");
                        UI_FXML.multi_result = "Both Lost";
                        outputLine = "Both Wrong\n";
                        new Multi_End();
                    }
                    out.println(outputLine);
                    if (inputLine.equals("stop_communication"))
                        break;
                } else {
                    Thread.sleep(700);
                    out.println("waiting");
                }
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "+port+" or listening for a connection");
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*

*/
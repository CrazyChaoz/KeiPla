package GUI_source;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by testuser on 09.05.2017.
 */
public class Multiplayer_Client extends Thread{

    InetAddress ip;
    int port;


    public Multiplayer_Client(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        try(
                Socket socket=new Socket(ip, port);
                PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            UI_FXML.multiplayer=1;
            String fromServer;
            System.out.println("connected to "+ip+" on port "+port);


            fromServer = in.readLine();
            System.out.println("Question recieved "+fromServer);
            String[] s;
            s=fromServer.split("\\n");
            UI_FXML.currQuestion=s[0].split(";");

            new Multiplayer_Game();

            System.out.println("reachable?");

            //initiate the waiting communication
            out.println();
            while ((fromServer = in.readLine())!=null){
                System.out.println("Server: " + fromServer);
                if(UI_FXML.lock==0){

                    System.out.println("Client: " + UI_FXML.multi_result);
                    out.println(UI_FXML.multi_result+"\n");
                    UI_FXML.multi_result=null;
                    UI_FXML.lock=1;

                    if(fromServer.equals("You Won")){
                        System.out.println("You Won");
                        UI_FXML.multi_result="You Won";
                        new Multi_End();
                    }else if (fromServer.equals("You Lost")){
                        System.out.println("You Lost");
                        UI_FXML.multi_result="You Lost";
                        new Multi_End();
                    }else if (fromServer.equals("Both Lost")){
                        System.out.println("Both Lost");
                        UI_FXML.multi_result="Both Lost";
                        new Multi_End();
                    }else{
                        System.out.println("Question recieved "+fromServer);
                        s=fromServer.split("\\n");
                        UI_FXML.currQuestion=s[0].split(";");

                        new Multiplayer_Game();
                    }
                }else{
                    Thread.sleep(1000);
                    out.println("waiting");
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +port);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*

 */
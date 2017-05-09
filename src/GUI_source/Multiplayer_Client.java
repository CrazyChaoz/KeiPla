package GUI_source;

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
public class Multiplayer_Client extends Thread {

    InetAddress ip;
    int port;

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

            System.out.println("Starting the MultiGame");
            UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
            UI_FXML.currStage.setTitle("DAS SPIEL");
            Scene scene=(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
            scene.setFill(Color.TRANSPARENT);
            UI_FXML.currStage.setScene(scene);
            UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
            UI_FXML.currStage.show();


            while ((fromServer = in.readLine())!=null) {
                System.out.println("Server: " + fromServer);
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
                    scene=(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
                    UI_FXML.currStage.setScene(scene);
                    UI_FXML.currStage.show();
                }



                while(true) {
                    if(UI_FXML.lock==0){
                        System.out.println("Client: " + UI_FXML.multi_result);
                        out.println(UI_FXML.multi_result+"\n");
                        UI_FXML.multi_result=null;
                        UI_FXML.lock=1;
                        break;
                    }
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + ip);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +port);
        }
    }

    public Multiplayer_Client(InetAddress ip, int port) {
        this.ip = ip;
        this.port = port;
    }
}

package GUI_source;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.*;

public class Multiplayer{

    public String selected;

    public void startServerAction(int port){
        Stage game=null;
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e){}
        try(
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){

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

            while ((inputLine = in.readLine()) != null){
                System.out.println("ClientMSG: "+inputLine);
                if(inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])])&&selected.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])])){
                    System.out.println("Both Right");
                    outputLine = UI_FXML.currQuestion[0]+";"+
                            UI_FXML.currQuestion[1]+";"+
                            UI_FXML.currQuestion[2]+";"+
                            UI_FXML.currQuestion[3]+";"+
                            UI_FXML.currQuestion[4]+";"+
                            "1337"+"\n";
                    try {
                        System.out.println("Starting the ServerGame");
                        UI_FXML.hardness=1;

                        game=new Stage(StageStyle.TRANSPARENT);
                        game.setTitle("DAS SPIEL");Scene s=(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
                        s.setFill(Color.TRANSPARENT);
                        game.setScene(s);
                        game.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                        game.show();
                    } catch(IOException e){}

                }else if(!inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])])&&selected.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])])){
                    System.out.println("You Won");
                    UI_FXML.multi_result="You Won";
                    outputLine="You Lost\n";
                    game.close();
                    Multi_End();
                }else if(inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])])&&!selected.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])])){
                    System.out.println("You Lost");
                    UI_FXML.multi_result="You Lost";
                    outputLine="You Won\n";
                    game.close();
                    Multi_End();
                }else{
                    System.out.println("Both Wrong");
                    UI_FXML.multi_result="Both Lost";
                    outputLine="Both Wrong\n";
                    game.close();
                    Multi_End();
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
        Stage game=null;
        try(
                Socket socket=new Socket(ip, port);
                PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){
            UI_FXML.multiplayer=1;
            String fromServer;
            System.out.println("connected to "+ip+" on port "+port);

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if(fromServer.equals("You Won")){
                    System.out.println("You Won");
                    UI_FXML.multi_result="You Won";
                    game.close();
                    Multi_End();
                }else if (fromServer.equals("You Lost")){
                    System.out.println("You Lost");
                    UI_FXML.multi_result="You Lost";
                    game.close();
                    Multi_End();
                }else if (fromServer.equals("Both Lost")){
                    System.out.println("Both Lost");
                    UI_FXML.multi_result="Both Lost";
                    game.close();
                    Multi_End();
                }else{
                    System.out.println("Question recieved "+fromServer);
                    String[] s;
                    s=fromServer.split("\\n");
                    UI_FXML.currQuestion=s[0].split(";");
                }

                try {
                    System.out.println("Starting the ClientGame");
                    UI_FXML.hardness=1;
                    game=new Stage(StageStyle.TRANSPARENT);
                    game.setTitle("DAS SPIEL");Scene s=(new Scene(FXMLLoader.load(getClass().getResource("Ingame.fxml"))));
                    s.setFill(Color.TRANSPARENT);
                    game.setScene(s);
                    game.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
                    game.show();
                } catch(IOException e){}

                while(true) {
                    if(UI_FXML.lock ==false) {
                        System.out.println("Client: " + UI_FXML.multi_result);
                        out.println(UI_FXML.multi_result + "\n");
                        UI_FXML.multi_result = null;
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

    private void Multi_End(){

        try {
            UI_FXML.currStage.close();
            UI_FXML.currStage=new Stage(StageStyle.TRANSPARENT);
            UI_FXML.currStage.setTitle("Score");
            Scene s=new Scene(FXMLLoader.load(getClass().getResource("Multi_end.fxml")));
            s.setFill(Color.TRANSPARENT);
            UI_FXML.currStage.setScene(s);
            UI_FXML.currStage.getIcons().add(new Image(this.getClass().getResourceAsStream("res"+ File.separator+"KeiPla-Icon-128.png")));
            UI_FXML.currStage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)throws Exception{
        Multiplayer multiplayer=new Multiplayer();
        int i=0;
        if(i==0) {
            multiplayer.startClientAction(InetAddress.getByName("127.0.0.1"), 63956);
        }else{
            multiplayer.startServerAction(63956);
        }
    }
}


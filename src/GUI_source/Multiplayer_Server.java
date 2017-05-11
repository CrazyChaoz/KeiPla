package GUI_source;


import java.io.*;
import java.net.*;

public class Multiplayer_Server extends Thread{

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
            System.out.println(Thread.currentThread());
            */

            out.println("waiting");

            while ((inputLine = in.readLine())!=null) {
                System.out.println("Client: "+inputLine);
                System.out.println("Server: "+UI_FXML.multi_result);

                Boolean otherSolution;
                Boolean ownSolution;

                if(!inputLine.equals("waiting")&&UI_FXML.multi_result==null){
                    otherSolution = inputLine.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])]);
                    ownSolution = UI_FXML.multi_result.equals(UI_FXML.currQuestion[Integer.parseInt(UI_FXML.currQuestion[5])]);

                    if (otherSolution && ownSolution){
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
                    }else if(!otherSolution&&!ownSolution){
                        System.out.println("Both Wrong");
                        UI_FXML.multi_result = "Both Lost";
                        outputLine = "Both Wrong\n";
                        new Multi_End();
                    }else if (inputLine.equals("stop_communication")) {
                        break;
                    }
                }else{
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
}

/*

*/
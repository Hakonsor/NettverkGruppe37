package no.hioa.trafficlight;

import no.hioa.trafficlight.model.Port;
import java.io.*;
import java.net.*;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.hioa.trafficlight.view.ClientAppFXMLController;

public class Client implements Runnable {

    private static ClientAppFXMLController controller;
    private int portNumber;
    private String hostName;
    private boolean connected = false;
    private boolean connecting = true;
    private Thread thread;
    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out; 

    public void setController(ClientAppFXMLController controller2) {
        controller = controller2;
    }

//Port port, InetAddress inetAddress
    public Client() {
        hostName = "localhost";
        portNumber = 1337; //Integer.parseInt(args[1]);

    }

    public Client(int port, String adress) {
        hostName = adress;
        portNumber = port; //Integer.parseInt(args[1]);
    }

    public void setStartConnection(int port, String adress) {
        hostName = adress;
        portNumber = port;
        thread = new Thread(this);
        thread.start();
    }

    public void Disconnect(){
        controller.appendText("Disconnecting");
        if (thread != null) {
            //try {
                //in.close();
                //out.close();
                //socket.close();
                
            //} catch (IOException ex) {
           //     Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
           // }
        }else{
            controller.appendText("Nothing to disconnect");
        }
        connecting = false;
        connected = false;
    }

    @Override
    public void run() {
        connecting = true;
        controller.appendText("Connecting: " + hostName + ":" + portNumber);
        while (connecting) {
            try (
                    Socket newSocket = new Socket(hostName, portNumber);
                    PrintWriter newOut = new PrintWriter(newSocket.getOutputStream(), true);
                    BufferedReader newIn = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));) {
                //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                
                socket = newSocket;
                in = newIn;
                out = newOut;
                
                controller.appendText("Connected to: " + hostName + ":" + portNumber);
                connecting = false;
                connected = true;
                String inputServer;
                String outputClient;

                while ((inputServer = in.readLine()) != null) {
                    System.out.println("WHHAT!?");
                    controller.appendText("Server: " + inputServer);
                    controller.update(inputServer);
                    if (inputServer.equals("Bye.")) {
                        break;
                    }
                    /*
                outputClient = stdIn.readLine();
                if (outputClient != null) {
                    System.out.println("Client: " + outputClient);
                    out.println(outputClient);
                }*/

                }
                System.out.println("ute av løkken");
                connected = false;
            } catch (UnknownHostException e) {
                controller.appendText("Cant find the server: " + hostName);
                controller.appendText("New attemmpt in 5 secunds");
                try {
                    Thread.sleep(5000);
                    // System.exit(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.exit(1);
            } catch (IOException e) {
                controller.appendText("Input/Output error for the connection to "
                        + hostName);

                controller.appendText("New attemmpt in 5 secunds");

                try {
                    Thread.sleep(5000);
                    // System.exit(1);
                } catch (InterruptedException ex) {
                   
                }
            } catch (Exception e) {
                System.out.println("meh");
            }
        }
        controller.appendText("Disconnected");
       
    }
}

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
    
    public void setStartConnection(int port, String adress){
        hostName = adress;
        portNumber = port;
        new Thread(this).start();
    }

    public void Disconnect() {
        connecting = false;
        connected = false;
    }

    @Override
    public void run() {
        connecting = true;
        while (connecting) {
            try (
                    Socket socket = new Socket(hostName, portNumber);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
                //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                connecting = false;
                connected = true;
                String inputServer;
                String outputClient;

                while ((inputServer = in.readLine()) != null) {
                    System.out.println("Server: " + inputServer);
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
                connected = false;
            } catch (UnknownHostException e) {
                System.err.println("Cant find the server: " + hostName);
                System.out.println("New attemmpt in 5 secunds");
                try {
                    Thread.sleep(5000);
                    // System.exit(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.exit(1);
            } catch (IOException e) {
                System.err.println("Input/Output error for the connection to "
                        + hostName);
                System.out.println("New attemmpt in 5 secunds");
                try {
                    Thread.sleep(5000);
                    // System.exit(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

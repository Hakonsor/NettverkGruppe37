package no.hioa.trafficlight;

import no.hioa.trafficlight.model.Port;
import java.io.*;
import java.net.*;
import java.net.InetAddress;
import no.hioa.trafficlight.view.ClientAppFXMLController;

/**
 * Created by Simen on 28.02.2016.
 */
public class Client {

    private ClientAppFXMLController controller;

   

    public void setController(ClientAppFXMLController controller) {
        this.controller = controller;
    }
//Port port, InetAddress inetAddress
    public Client() {

        String hostName = "localhost";
        int portNumber = 1337; //Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
            //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
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
        } catch (UnknownHostException e) {
            System.err.println("Cant find the server: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Input/Output error for the connection to "
                    + hostName);
            System.exit(1);
        }
    }

    public void Disconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

package no.hioa.trafficlight;

import no.hioa.trafficlight.model.Port;
import java.io.*;
import java.net.*;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.hioa.trafficlight.view.ClientAppFXMLController;

public class Client implements Runnable {

    private ClientAppFXMLController controller;
    private int portNumber;
    private String hostName;
    private volatile boolean running;

    public void setController(ClientAppFXMLController controller2) {
        controller = controller2;
    }

    //Port port, InetAddress inetAddress
    public Client(ClientAppFXMLController cafc) {
        controller = cafc;
        hostName = "localhost";
        portNumber = 1337;
    }

    public Client(int port, String adress) {
        hostName = adress;
        portNumber = port;
    }

    public void setStartConnection(int port, String adress) {
        hostName = adress;
        portNumber = port;
        new Thread(this).start();
    }

    public void terminate(Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.write('1');
        } catch (IOException e) {
            System.out.println("Something went wrong in sending termination signal to server.");
        }
        running = false;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                Socket socket = new Socket(hostName, portNumber);
                controller.setSocket(socket);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String inputServer;
                String outputClient;

                //
                while ((inputServer = in.readLine()) != null && socket.isConnected()) {
                    System.out.println("Server: " + socket.getInetAddress().getHostAddress());

                    System.out.println(inputServer);
                    String spilt[] = inputServer.split(",", 4);
                    controller.update(inputServer);
                    System.out.println(spilt.length);

                    if (spilt.length == 4) {

                        if (spilt[1] != null && !spilt[1].equals("")) {
                            System.out.println("run");
                            controller.timerSlider(inputServer);
                        } else if (spilt[0] != null) {
                            controller.stopTimerSlider();
                        }
                    }
                    System.out.println(spilt);
                    /*
                outputClient = stdIn.readLine();
                if (outputClient != null) {
                    System.out.println("Client: " + outputClient);
                    out.println(outputClient);
                }*/

                }
            } catch (UnknownHostException e) {
                if (controller.getDisconnectUsed()) {
                    terminate(controller.getSocket());
                    System.out.println("Client thread terminated.");
                } else {
                    while (!controller.getDisconnectUsed()) {
                        try {
                            System.err.println("Can't find server with host name: " + hostName);
                            System.out.println("New attempt in 10 seconds");
                            Thread.sleep(10000);
                            // System.exit(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    terminate(controller.getSocket());
                    System.out.println("Client thread terminated.");
                }

                //System.exit(1);
            } catch (IOException e) {
                if (controller.getDisconnectUsed()) {
                    terminate(controller.getSocket());
                    System.out.println("Client thread terminated in IOException catch ");
                } else {
                    System.err.println("Input/Output error for the connection to "
                            + hostName);
                    System.out.println("New attempt in 5 seconds");
                    while (!controller.getDisconnectUsed()) {
                        try {
                            Thread.sleep(5000);
                            // System.exit(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    terminate(controller.getSocket());
                    System.out.println("Client thread terminated at end.");
                }
            }

        }
    }
}

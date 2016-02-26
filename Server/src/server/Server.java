/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.*;
import java.io.*;
import java.util.HashSet;

/**
 *
 * @author hakon
 */
public class Server {
    static HashSet<InetAddress> inetAddresses = new HashSet<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int portNumber = 27182; // Default port

        /**
         * Port number can be defined in the first commandline argument
         */
        if (args.length > 0) {
            if (args.length == 1) {
                portNumber = Integer.parseInt(args[0]);
            } else {
                System.err.println("Usage: java Server [<port number>]");
                System.exit(1);
            }
        }

        System.out.println("Server is using port number " + portNumber + "\nListening for connection attempts:");

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            String receivedText;
            // Continually listening for clients
            while (true) {
                ClientServer clientserver = new ClientServer(serverSocket.accept());
                clientserver.start();
                System.out.println("Client with IP " + clientserver.clientAddr.toString() + " connected.");
            }
        } catch (IOException e) {
            System.out.println("Exception occurred when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }

    private static class ClientServer extends Thread {
        Socket connectSocket;
        InetAddress clientAddr;

        public ClientServer(Socket connectSocket) throws IOException {
            this.connectSocket = connectSocket;
            clientAddr = connectSocket.getInetAddress();
            if (!inetAddresses.add(clientAddr)) {
                connectSocket.close();
            }
        }

        public void run() {
            // This if-test allows a connection if the current ip is not in the HashSet of addresses
                try (PrintWriter out = new PrintWriter(connectSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(connectSocket.getInputStream()));
                ) {
                    String receivedText;
                    while (((receivedText = in.readLine()) != null)) {
                        System.out.println("Client [" + clientAddr.getHostAddress() + "]: > " + receivedText);
                        String ucaseText = receivedText.toUpperCase();
                        out.println(ucaseText);
                        System.out.println("Server [" + InetAddress.getLocalHost().getHostAddress() + "]: > " + ucaseText);
                    }
                    inetAddresses.remove(clientAddr);
                    out.println("Disconnected.");
                    System.out.println("Client with IP " + clientAddr.toString() + " disconnected.");
                    connectSocket.close();
                } catch (IOException e) {
                    System.out.println("Exception occurred when trying to communicate with the client " + clientAddr.getHostAddress());
                    System.out.println(e.getMessage());
                }
        }
    }
}

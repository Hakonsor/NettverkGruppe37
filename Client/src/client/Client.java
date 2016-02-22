/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostName = "127.0.0.1"; // Default host, localhost
        int portNumber = 27182; // Default port
        if (args.length > 0) {
            hostName = args[0];
            if (args.length > 1) {
                System.err.println("Usage: java Client [<host name>] [<port number>]");
                System.exit(1);
            }
            if (args.length > 2) {
                System.err.println("Usage: java Client [<host name>] [<port number>]");
                System.exit(1);
            }
        }
        System.out.print("Hostname: " + hostName + "\n Port number: " + portNumber);

        try (
                Socket trafficClientSocket = new Socket(hostName, portNumber);

                PrintWriter out = new PrintWriter(trafficClientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(trafficClientSocket.getInputStream()));

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            System.out.print("Client [" + InetAddress.getLocalHost().getHostAddress() + "]: > ");
            while ((userInput = stdIn.readLine()) != null && !userInput.isEmpty()) {
                out.println(userInput);
                String receivedText = in.readLine();
                System.out.println("Server [" + hostName + "]: > " + receivedText);
                System.out.print("Client [" + InetAddress.getLocalHost().getHostAddress() + "]: > ");
            }

        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No I/O for connection to " + hostName);
            System.exit(1);
        }

    }
    
}

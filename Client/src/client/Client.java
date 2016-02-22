/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.net.*;
 
public class Client {
    public static void main(String[] args) throws IOException {
         
        /*
        if (args.length != 2) {
            System.err.println(
                "Usage: java Client <host name> <port number>");
            System.exit(1);
        }
        */
        String hostName = "localhost";
        int portNumber = 1337; //Integer.parseInt(args[1]);
 
        try (
            Socket socket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String inputServer;
            String outputClient;

            while ((inputServer = in.readLine()) != null) {
                System.out.println("Server: " + inputServer);
                if (inputServer.equals("Bye."))
                    break;
                 
                outputClient = stdIn.readLine();
                if (outputClient != null) {
                    System.out.println("Client: " + outputClient);
                    out.println(outputClient);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Cant find the server: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Input/Output error for the connection to " +
                hostName);
            System.exit(1);
        }
    }
}
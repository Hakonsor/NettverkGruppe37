/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hakon
 */
public class OServer{
        static int portNumber;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length != 1) {
            System.err.println("Server allere igang <port number>");
            System.exit(1);
        }
        portNumber = Integer.parseInt(args[0]);
    }
    public void startServer(int port){
            portNumber = port;
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
         ) {
            String input, output;
             
            // Initiate conversation with client
            Protocol p = new Protocol();
            output = p.getState();
            out.println(output);
 
            while ((input = in.readLine()) != null) {
                output = p.getState();
                out.println(output);
                if (output.equals("Disconnect."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Feil ved portnummer: "+ portNumber);
            System.out.println(e.getMessage());
        }
    }
}

package no.hioa.trafficlight;
import no.hioa.trafficlight.model.Port;

/**
 * Created by Simen on 28.02.2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
/**
 *
 * @author hakon
 */



public class Server {

     static int portNumber;
    public Server(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java MultiServer <port number>");
            System.exit(1);
        }
         portNumber = Integer.parseInt(args[0]);
    }

    public Server(int portnumber) {
         portNumber = portnumber;
    }
    
    public Server(){
         portNumber = 1337;
    }

    public static void startServer() throws IOException {
        
        boolean listening = true;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

    public void setPort(int port) {
        portNumber = port;
    }

}
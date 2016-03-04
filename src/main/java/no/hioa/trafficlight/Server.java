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
import java.util.ArrayList;
import java.util.List;
import no.hioa.trafficlight.view.ClientAppFXMLController;
import no.hioa.trafficlight.view.ServerAppFXMLController;
/**
 *
 * @author hakon
 */



public class Server implements Runnable{

    private static int portNumber = 1337;
    private List<ServerThread> list = new ArrayList<>();
    private ServerAppFXMLController controller;
    
    public void setController(ServerAppFXMLController controller){
        this.controller = controller;
    }

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
       
    }

    public static void setPort(int port) {
        portNumber = port;
    }
    


    @Override
    public void run() {


        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (!Thread.interrupted()) {
                ServerThread thread = new ServerThread(serverSocket.accept());
                thread.start();
                list.add(thread);
                controller.setThreadList(list);
                
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }

    public void setInstruction(String adress, String intervall) {
        
        list.forEach(e ->{
        if(e.getAdress().getHostAddress().equals(adress)||e.getAdress().getHostName().equals(adress))
            e.setInstruction(intervall);
        });
    }

    public void setInstructionAll(String intervall) {
       list.forEach(e ->{
            e.setInstruction(intervall);
        });
    }

}
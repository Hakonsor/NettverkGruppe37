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
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author hakon
 */
public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ServerFXML.fxml"));

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Server");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        portNumber = 1337;
        Server s = new Server();
        launch(args);
        s.startServer(1337);
        
    }

    private static int portNumber;

    public void startServer(int port) {
        portNumber = port;
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String input, output;

            // Initiate conversation with client
            Protocol p = new Protocol();
            output = p.getState();
            out.println(output);

            while ((input = in.readLine()) != null) {
                output = p.getState();
                out.println(output);
                if (output.equals("Disconnect.")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Feil ved portnummer: " + portNumber);
            System.out.println(e.getMessage());
        }
    }
}

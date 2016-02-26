/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author hakon
 */
public class Main extends Application {
    
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        mainMindow();
    }
    
    public void mainMindow(){
         
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ServerFXML.fxml"));
            AnchorPane pane = loader.load();
            
            ServerFXMLController controller = loader.getController();
            
            Scene scene = new Scene(pane);
            
            primaryStage.setTitle("Server");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //Server s = new Server();
            MultiServer s = new MultiServer(1337);
            controller.setServer(s);
            new Thread(){
                 public void run(){
                     try {
                         s.startMultiServer();
                         //      s.startServer(1337);
                     } catch (IOException ex) {
                         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }  
            }.start();
          
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
        
        
    }
    
}

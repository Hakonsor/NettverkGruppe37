/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientFXML.fxml"));
            AnchorPane pane = loader.load();
            
            ClientFXMLController controller = loader.getController();
            
            Scene scene = new Scene(pane);
            
            primaryStage.setTitle("Client");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            Client c = new Client();
            controller.setClient(c);
            new Thread(){
                 public void run(){
                  c.setController(controller);
                  c.startClient(1337, "localhost");
             
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

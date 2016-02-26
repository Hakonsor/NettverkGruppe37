/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author hakon
 */
public class ServerFXMLController implements Initializable {

    @FXML 
    private RadioButton radiobuttonOff;
    @FXML 
    private RadioButton radiobuttonRed;
    @FXML 
    private RadioButton radiobuttonYellow;
    @FXML 
    private RadioButton radiobuttonGreen;
    @FXML 
    private Slider Slider_Red;
    @FXML 
    private Slider Slider_Yellow;
    @FXML 
    private Slider Slider_Green;
    @FXML 
    private Circle lightRed;
    @FXML 
    private Circle lightYellow;
    @FXML 
    private Circle lightGreen;
    @FXML 
    private TextArea textAreaServer;
    @FXML 
    private ListView listviewClients;
    @FXML
    private ToggleGroup radiobutton;
    
    
    private MultiServer server; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onRadioButtonClicked(ActionEvent event) {
        if(radiobuttonOff.isArmed()){
            lightRed.setFill( Color.web("#797979"));
            lightYellow.setFill( Color.web("#797979"));
            lightGreen.setFill( Color.web("#797979"));
           Protocol.setBlinkingYellow();

        }else if(radiobuttonRed.isArmed()){
            lightRed.setFill(Color.RED);
            lightYellow.setFill( Color.web("#797979"));
            lightGreen.setFill( Color.web("#797979"));
            Protocol.setRed();

        }else if(radiobuttonYellow.isArmed()){
            lightRed.setFill( Color.web("#797979"));
            lightYellow.setFill(Color.YELLOW);
            lightGreen.setFill( Color.web("#797979"));
            Protocol.setYellow();

        }else if(radiobuttonGreen.isArmed()){
            lightRed.setFill( Color.web("#797979"));
            lightYellow.setFill( Color.web("#797979"));
            lightGreen.setFill(Color.GREEN);
            Protocol.setGreen();
        }
    }

    public void setServer(MultiServer server) {
        this.server = server;
    }




    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author hakon
 */
public class ServerFXMLController implements Initializable {

    @FXML 
    private RadioButton radiobuttonOff;
    @FXML 
    private RadioButton radiobuttonRed;
    @FXML 
    private RadioButton radiobuttonYellow;
    @FXML 
    private RadioButton radiobuttonGreen;
    @FXML 
    private Slider Slider_Red;
    @FXML 
    private Slider Slider_Yellow;
    @FXML 
    private Slider Slider_Green;
    @FXML 
    private Circle lightRed;
    @FXML 
    private Circle lightYellow;
    @FXML 
    private Circle lightGreen;
    @FXML 
    private TextArea textAreaServer;
    @FXML 
    private ListView listviewClients;
    @FXML
    private ToggleGroup radiobutton;
    
    
    private MultiServer server; 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onRadioButtonClicked(ActionEvent event) {
        if(radiobuttonOff.isArmed()){
            lightRed.setFill( Color.web("#797979"));
            lightYellow.setFill( Color.web("#797979"));
            lightGreen.setFill( Color.web("#797979"));
           Protocol.setBlinkingYellow();

        }else if(radiobuttonRed.isArmed()){
            lightRed.setFill(Color.RED);
            lightYellow.setFill( Color.web("#797979"));
            lightGreen.setFill( Color.web("#797979"));
            Protocol.setRed();

        }else if(radiobuttonYellow.isArmed()){
            lightRed.setFill( Color.web("#797979"));
            lightYellow.setFill(Color.YELLOW);
            lightGreen.setFill( Color.web("#797979"));
            Protocol.setYellow();

        }else if(radiobuttonGreen.isArmed()){
            lightRed.setFill( Color.web("#797979"));
            lightYellow.setFill( Color.web("#797979"));
            lightGreen.setFill(Color.GREEN);
            Protocol.setGreen();
        }
    }

    public void setServer(MultiServer server) {
        this.server = server;
    }




    
}

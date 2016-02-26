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
        timerSlider();
    }

    @FXML
    private void onRadioButtonClicked(ActionEvent event) {
        if (radiobuttonOff.isArmed()) {
            changeToOff();
            Protocol.setBlinkingYellow();

        } else if (radiobuttonRed.isArmed()) {
            changeToRed();
            Protocol.setRed();

        } else if (radiobuttonYellow.isArmed()) {
            changeToYellow();
            Protocol.setYellow();

        } else if (radiobuttonGreen.isArmed()) {
            changeToGreen();
            Protocol.setGreen();
        }
    }

    public void setServer(MultiServer server) {
        this.server = server;// trenger ikke dette lengere
        
    }

    private void changeToRed() {
        lightRed.setFill(Color.RED);
        lightYellow.setFill(Color.web("#797979"));
        lightGreen.setFill(Color.web("#797979"));
    }

    private void changeToYellow() {
        lightRed.setFill(Color.web("#797979"));
        lightYellow.setFill(Color.YELLOW);
        lightGreen.setFill(Color.web("#797979"));
    }

    private void changeToGreen() {
        lightRed.setFill(Color.web("#797979"));
        lightYellow.setFill(Color.web("#797979"));
        lightGreen.setFill(Color.GREEN);
    }

    private void changeToOff() {
        lightRed.setFill(Color.web("#797979"));
        lightYellow.setFill(Color.web("#797979"));
        lightGreen.setFill(Color.web("#797979"));
    }

    public void timerSlider() {

        new Thread() {
            public void run() {
                Slider currentSlider = Slider_Red;
                String prevState = Protocol.getState();
                long millis = System.currentTimeMillis();

                while (true) {
                    if (prevState.equals(Protocol.getState())) {
                        if ((long) currentSlider.getValue() * 100 < System.currentTimeMillis() - millis) {
                            millis = System.currentTimeMillis();
                            switch (Protocol.getState()) {
                                case "RED":
                                    currentSlider = Slider_Yellow;
                                    changeToYellow();
                                    Protocol.setYellow();
                                    break;
                                case "YELLOW":
                                    changeToGreen();
                                    currentSlider = Slider_Green;
                                    Protocol.setGreen();
                                    break;
                                case "GREEN":
                                    currentSlider = Slider_Red;
                                    changeToRed();
                                    Protocol.setRed();
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        prevState = Protocol.getState();
                        millis = System.currentTimeMillis();
                        switch (Protocol.getState()) {
                            case "RED":
                                currentSlider = Slider_Red;
                                break;
                            case "YELLOW":
                                currentSlider = Slider_Yellow;
                                break;
                            case "GREEN":
                                currentSlider = Slider_Green;
                                break;
                            default:
                                break;
                        }

                    }
                }
            }

        }.start();

    }

}

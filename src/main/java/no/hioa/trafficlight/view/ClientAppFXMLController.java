package no.hioa.trafficlight.view;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import no.hioa.trafficlight.Client;
import no.hioa.trafficlight.ClientApp;
import no.hioa.trafficlight.model.Port;

/**
 * Created by Simen on 28.02.2016.
 */
public class ClientAppFXMLController implements Initializable {

    private Socket socket;

    private boolean disconnectUsed = false;

    private Client client;

    @FXML
    private Button buttonOff;
    @FXML
    private Circle lightRed;
    @FXML
    private Circle lightYellow;
    @FXML
    private Circle lightGreen;
    @FXML
    private TextArea textarea;
    @FXML
    private TextField textfield_adress;
    @FXML
    private TextField textfield_port;
    @FXML
    private Button button_connect;
    @FXML
    private Button button_disconnect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }

    @FXML
    private void onButtonActionClicked(ActionEvent event) {

        if (event.getSource().equals(buttonOff)) {
            update("OFF");
        } else if (event.getSource().equals(button_connect)) {
            connect();
        } else if (event.getSource().equals(button_disconnect)) {
            disconnect();
        }
    }

    public void update(String inputServer) {
        System.out.println(inputServer);
        switch (inputServer) {
            case "OFF":
                lightRed.setFill(Color.web("#797979"));
                lightYellow.setFill(Color.web("#797979"));
                lightGreen.setFill(Color.web("#797979"));
                textarea.appendText("Traffic display off.\n");
            case "BLINKING_YELLOW":
                lightRed.setFill(Color.web("#797979"));
                lightYellow.setFill(Color.web("#797979"));
                lightGreen.setFill(Color.web("#797979"));
                break;
            case "GREEN":
                lightRed.setFill(Color.web("#797979"));
                lightYellow.setFill(Color.web("#797979"));
                lightGreen.setFill(Color.GREEN);
                break;
            case "YELLOW":
                lightRed.setFill(Color.web("#797979"));
                lightYellow.setFill(Color.YELLOW);
                lightGreen.setFill(Color.web("#797979"));
                break;
            case "RED":
                lightRed.setFill(Color.RED);
                lightYellow.setFill(Color.web("#797979"));
                lightGreen.setFill(Color.web("#797979"));
                break;
            default:
                lightRed.setFill(Color.web("#797979"));
                lightYellow.setFill(Color.web("#797979"));
                lightGreen.setFill(Color.web("#797979"));
                break;
        }

    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void connect() {
        String ports = textfield_port.getText();
        String address;
        try {
            Port port = new Port(Integer.parseInt(ports));
            client.setStartConnection(port.getPort(), textfield_adress.getText());
            address = socket.getInetAddress().getHostAddress();
            textarea.appendText("Connected to " + address + ":" + ports + "\n");
        } catch (Exception ex) {
            System.out.println("Ugyldig port eller adresse");
            textarea.appendText("Could not connect to " + textfield_adress.getText() + ":" + ports + "\n");
        }
    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
                disconnectUsed = true;
                textarea.appendText("Disconnected from " + textfield_adress.getText() + ":" + "\n");
            } else {textarea.appendText("The client is not connected to a server.\n");}

        } catch (IOException e) {
            System.err.println("Something went wrong while disconnecting the server.");
            textarea.appendText("Something went wrong while disconnecting from the server.\n");
        }
    }

    public boolean getDisconnectUsed() {
        return disconnectUsed;
    }

    public static void pause(int seconds){
        Date start = new Date();
        Date end = new Date();
        while(end.getTime() - start.getTime() < seconds * 1000){
            end = new Date();
        }
    }

}

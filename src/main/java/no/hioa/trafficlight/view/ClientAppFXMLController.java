package no.hioa.trafficlight.view;

import java.net.URL;
import java.util.ResourceBundle;
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
            client.Disconnect();
        } else if (event.getSource().equals(button_connect)) {
            try {
                String ports = textfield_port.getText();
                Port port = new Port(Integer.parseInt(ports));
                client.setStartConnection(port.getPort(), textfield_adress.getText());
            } catch (Exception ex) {

                System.out.println("Uyldig port eller adresse");

            }
           
        } else if (event.getSource().equals(button_disconnect)) {
            client.Disconnect();
        }
    }

    public void update(String inputServer) {
        System.out.println("bytte");
        switch (inputServer) {
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

}

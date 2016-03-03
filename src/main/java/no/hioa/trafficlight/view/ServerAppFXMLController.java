package no.hioa.trafficlight.view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import no.hioa.trafficlight.Server;
import no.hioa.trafficlight.ServerApp;
import no.hioa.trafficlight.model.InvalidPortException;
import no.hioa.trafficlight.model.Port;

/**
 * Created by Simen on 28.02.2016.
 */
public class ServerAppFXMLController {

    public ServerApp serverApp;
    public Server server;
    public Button manualOff;
    public Button manualRed;
    public TextField serverSettingsPortField;
    public Button manualYellow;
    public Button manualGreen;
    public Button manualError;
    public TextField instructionRed;
    public TextField instructionYellow;
    public TextField instructionGreen;
    public Button instructionSend;
    public TextArea serverInfoField;
    public ListView<String> clientList;
    public Button serverSettingsStartButton;

    public void manualOffAction(ActionEvent actionEvent) {
        serverInfoField.appendText("Traffic lights are set to off.\n");
    }

    public void manualRedAction(ActionEvent actionEvent) {
        serverInfoField.appendText("Traffic lights are set to red.\n");
    }

    public void manualYellowAction(ActionEvent actionEvent) {
    }

    public void manualGreenAction(ActionEvent actionEvent) {
    }

    public void manualErrorAction(ActionEvent actionEvent) {
    }

    public void instructionRedAction(ActionEvent actionEvent) {
    }

    public void instructionYellowAction(ActionEvent actionEvent) {
    }

    public void instructionGreenAction(ActionEvent actionEvent) {
    }

    public void instructionSendAction(ActionEvent actionEvent) {
    }

    public void serverSettingsPortFieldAction(ActionEvent actionEvent) {
        
    }

    public void serverSettingsStartButtonAction(ActionEvent actionEvent) {
        String ports = serverSettingsPortField.getText();
        try {
            Port port = new Port(Integer.parseInt(ports));
            server.setPort(port.getPort());
            Server.startServer();
        } catch (InvalidPortException ex) {
            serverSettingsPortField.clear();

        } catch(Exception ex){
            serverSettingsPortField.clear();

        }
        
    }

    public void setServerApp(ServerApp serverApp) {
        this.serverApp = serverApp;
        clientList.setItems(serverApp.getInetAddresses());
    }

    public void setServer(Server server) {
        this.server = server;
    }
}

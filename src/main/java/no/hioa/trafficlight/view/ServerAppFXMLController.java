package no.hioa.trafficlight.view;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import no.hioa.trafficlight.ServerApp;

/**
 * Created by Simen on 28.02.2016.
 */
public class ServerAppFXMLController {
    public ServerApp serverApp;
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
    }

    public void manualRedAction(ActionEvent actionEvent) {
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
    }

    public void setServerApp(ServerApp serverApp) {
        this.serverApp = serverApp;
        clientList.setItems(serverApp.getInetAddresses());
    }
}

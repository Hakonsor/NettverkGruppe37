package no.hioa.trafficlight.view;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.hioa.trafficlight.Server;
import no.hioa.trafficlight.ServerApp;
import no.hioa.trafficlight.ServerThread;
import no.hioa.trafficlight.model.InvalidPortException;
import no.hioa.trafficlight.model.Port;

/**
 * Created by Simen on 28.02.2016.
 */
public class ServerAppFXMLController {

    public ServerApp serverApp;
    public Server server;
    public Thread currentServer;
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

    @FXML
    public void manualOffAction(ActionEvent actionEvent) {
        String intervall = "OFF,,,";
        if (getSelectedAdress() == null) {
            server.setInstructionAll(intervall);
            serverInfoField.appendText("Traffic lights are set to off.\n");
        } else {
            server.setInstruction(getSelectedAdress(), intervall);
            serverInfoField.appendText(getSelectedAdress()+"Traffic lights are set to off.\n");
        }
       
    }

    @FXML
    public void manualRedAction(ActionEvent actionEvent) {
        String intervall = "RED,,,";
        if (getSelectedAdress() == null) {
            server.setInstructionAll(intervall);
            serverInfoField.appendText("Traffic lights are set to Red.\n");
        } else {
            server.setInstruction(getSelectedAdress(), intervall);
            serverInfoField.appendText(getSelectedAdress()+"Traffic lights are set to Red.\n");
        }
        
    }

    @FXML
    public void manualYellowAction(ActionEvent actionEvent) {
    String intervall = "YELLOW,,,";
        if (getSelectedAdress() == null) {
            server.setInstructionAll(intervall);
            serverInfoField.appendText("Traffic lights are set to Yellow.\n");
        } else {
            server.setInstruction(getSelectedAdress(), intervall);
            serverInfoField.appendText(getSelectedAdress()+"Traffic lights are set to Yellow.\n");
        }
        
    }

    @FXML
    public void manualGreenAction(ActionEvent actionEvent) {
    String intervall = "GREEN,,,";
        if (getSelectedAdress() == null) {
            server.setInstructionAll(intervall);
            serverInfoField.appendText("Traffic lights are set to Green.\n");
        } else {
            server.setInstruction(getSelectedAdress(), intervall);
            serverInfoField.appendText(getSelectedAdress()+"Traffic lights are set to Green.\n");
        }
    }

    @FXML
    public void manualErrorAction(ActionEvent actionEvent) {
    String intervall = ",,,,";
        if (getSelectedAdress() == null) {
            server.setInstructionAll(intervall);
            serverInfoField.appendText("Traffic lights are set to Error.\n");
        } else {
            server.setInstruction(getSelectedAdress(), intervall);
            serverInfoField.appendText(getSelectedAdress()+"Traffic lights are set to Error.\n");
           
        }
    }

    @FXML
    public void instructionSendAction(ActionEvent actionEvent) {
        String intervall = ","+instructionRed.getText() + ",";
        intervall += instructionYellow.getText() + ",";
        intervall += instructionGreen.getText();
        if (getSelectedAdress() == null) {
            server.setInstructionAll(intervall);
            serverInfoField.appendText("Instruction send to all traffic lights.\n");
        } else {
            server.setInstruction(getSelectedAdress(), intervall);
            serverInfoField.appendText(getSelectedAdress()+" Instruction send to all traffic light.\n");
        }

    }

    @FXML
    public void serverSettingsPortFieldAction(ActionEvent actionEvent) {

    }

    @FXML
    public void serverSettingsStartButtonAction(ActionEvent actionEvent) {
        String ports = serverSettingsPortField.getText();
        try {
            Port port = new Port(Integer.parseInt(ports));
            Server.setPort(port.getPort());
            if (currentServer != null) {
                currentServer.interrupt();
                currentServer.start();
                System.out.println("server kj�rer");
            } else {
                currentServer = new Thread(server);
                currentServer.start();
                System.out.println("server kj�rer");
            }

        } catch (InvalidPortException ex) {
            serverSettingsPortField.clear();
            System.out.println("server failer");

        } catch (Exception ex) {
            serverSettingsPortField.clear();
            System.out.println("server failer");

        }

    }
    private String getSelectedAdress(){
        return clientList.getSelectionModel().getSelectedItem();
         
    }

    public void setServerApp(ServerApp serverApp) {
        this.serverApp = serverApp;
        clientList.setItems(serverApp.getInetAddresses());
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setThreadList(List<ServerThread> list) {
        ObservableSet<String> setInetAddresses = FXCollections.observableSet(); //
        ObservableList<String> listInetAddresses = FXCollections.observableArrayList();
        list.forEach(e -> {
            setInetAddresses.add(e.getAdress().getHostAddress());
        });
        listInetAddresses.setAll(setInetAddresses);
        clientList.setItems(listInetAddresses);
        serverInfoField.appendText(listInetAddresses.get(listInetAddresses.size()-1)+" has connected.\n");

    }

    @FXML
    private void instructionRedAction(ActionEvent event) {
    }

    @FXML
    private void instructionYellowAction(ActionEvent event) {
    }

    @FXML
    private void instructionGreenAction(ActionEvent event) {
    }
}

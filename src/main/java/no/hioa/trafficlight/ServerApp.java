/**
 * Created by Simen on 28.02.2016.
 */
package no.hioa.trafficlight;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hioa.trafficlight.view.ServerAppFXMLController;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    private AnchorPane serverLayout;
    private ObservableList<String> listInetAddresses = FXCollections.observableArrayList();
    private ObservableSet<String> setInetAddresses = FXCollections.observableSet(); //
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Traffic Light Server");

        serverWindow();
    }

    public void serverWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/ServerApp.fxml"));
            serverLayout = loader.load();
            
            Server server = new Server();
            

            ServerAppFXMLController serverAppFXMLController = loader.getController();
            serverAppFXMLController.setServer(server);
            
            
            

            Scene scene = new Scene(serverLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ObservableList<String> getInetAddresses() {
        setInetAddresses.add("Moo");
        setInetAddresses.add("Foo");
        setInetAddresses.add("Moo");
        setInetAddresses.add("Bar");
        listInetAddresses.addAll(setInetAddresses);
        return listInetAddresses;
    }
}

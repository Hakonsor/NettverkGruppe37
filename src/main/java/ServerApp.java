/**
 * Created by Simen on 28.02.2016.
 */

import javafx.application.Application;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Server server;
    private ObservableSet<InetAddress> inetAddresses; //
    private Stage primaryStage;
    private AnchorPane serverLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Traffic Light Server");
        serverWindow();
    }

    public void serverWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/ServerApp.fxml"));
            serverLayout = loader.load();

            Scene scene = new Scene(serverLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

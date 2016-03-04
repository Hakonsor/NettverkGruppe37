
package no.hioa.trafficlight;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import no.hioa.trafficlight.model.InvalidPortException;
import no.hioa.trafficlight.model.Port;
import no.hioa.trafficlight.view.ClientAppFXMLController;

public class ClientApp extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Traffic Light Client");

        serverWindow();
    }

    public void serverWindow() {

        try {
            AnchorPane clientLayout;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/ClientApp.fxml"));
            clientLayout = loader.load();

            ClientAppFXMLController clientAppFXMLController = loader.getController();
            Client client = new Client(clientAppFXMLController);
            clientAppFXMLController.setClient(client);
            client.setController(clientAppFXMLController);

            Scene scene = new Scene(clientLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

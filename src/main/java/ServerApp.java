/**
 * Created by Simen on 28.02.2016.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        serverWindow();
    }

    public void serverWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/ServerApp.fxml"));
            AnchorPane pane = loader.load();

        } catch (IOException e) {
            Logger.getLogger(ClientApp.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

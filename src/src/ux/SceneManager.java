package ux;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneManager {
    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }

    public void switchScene(String fxmlPath) {
        try {
            URL fxmlLocation = getClass().getResource(fxmlPath);
            if (fxmlLocation == null) {
                throw new IOException("FXML file not found: " + fxmlPath);
            }

            Parent root = FXMLLoader.load(fxmlLocation);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

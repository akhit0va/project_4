package ux;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ux.SceneManager;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    private static SceneManager sceneManager;

    @Override
    public void start(Stage primaryStage) throws IOException {
        sceneManager = new SceneManager(primaryStage);
        sceneManager.switchScene("/ux/views/login.fxml");
    }

    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

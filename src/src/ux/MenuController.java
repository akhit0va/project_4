package ux;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.User;
import java.io.IOException;

import static ux.Main.getSceneManager;

public class MenuController {

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void openCart() {
        getSceneManager().switchScene("/ux/views/cart.fxml");
    }

    @FXML
    private void openOrders() {
        getSceneManager().switchScene("/ux/views/order.fxml");
    }

    @FXML
    private void openProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ux/views/profile.fxml"));
            Parent root = loader.load();

            ProfileController profileController = loader.getController();
            profileController.setUser(currentUser);

            Stage stage = (Stage) getSceneManager().getStage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        getSceneManager().switchScene("/ux/views/login.fxml");
    }
}

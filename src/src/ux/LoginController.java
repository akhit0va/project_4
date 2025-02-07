package ux;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import repository.User.IUserRepository;
import repository.User.UserRepository;
import data.PostgresDB;

import java.io.IOException;

import static ux.Main.getSceneManager;

public class LoginController {
    @FXML private TextField emailField;

    @FXML private PasswordField passwordField;

    private final IUserRepository userRepository = new UserRepository(PostgresDB.getInstance());

    @FXML
    private void login() {
        String email = emailField.getText();

        String password = passwordField.getText();

        User user = userRepository.getAllUsers()
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getPasswordHash().equals(password))
                .findFirst()
                .orElse(null);

        if (user != null) {
            showAlert("Success", "Login successful!", Alert.AlertType.INFORMATION);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ux/views/menu.fxml"));
                Parent root = loader.load();
                MenuController menuController = loader.getController();
                menuController.setUser(user);

                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Error", "Invalid credentials.", Alert.AlertType.ERROR);
        }
    }




    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void openRegister() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ux/views/register.fxml"));
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.load(), 400, 500));
        stage.setTitle("Register");
        stage.show();
    }
    public void openRegister(ActionEvent actionEvent) {

    }
}



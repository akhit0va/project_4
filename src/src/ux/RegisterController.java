package ux;

import data.PostgresDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import repository.User.IUserRepository;
import repository.User.UserRepository;

import static ux.Main.getSceneManager;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private final IUserRepository userRepository = new UserRepository(PostgresDB.getInstance());

    @FXML
    private void register() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        User newUser = new User(0, username, email, password);
        boolean success = userRepository.createUser(newUser);

        if (success) {
            showAlert("Success", "Registration successful!", Alert.AlertType.INFORMATION);
            getSceneManager().switchScene("/ux/views/menu.fxml");
        } else {
            showAlert("Error", "Registration failed.", Alert.AlertType.ERROR);
        }
    }


    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}

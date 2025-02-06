package ux;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import data.PostgresDB;
import repository.User.UserRepository;
import service.UserService;

import static ux.Main.getSceneManager;

public class ProfileController {
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private final UserService userService;
    private User currentUser;

    public ProfileController() {
        this.userService = new UserService(new  UserRepository(PostgresDB.getInstance()));
    }

    public void setUser(User user) {
        this.currentUser = user;
        usernameField.setText(user.getUsername());
        emailField.setText(user.getEmail());
    }

    @FXML
    private void saveProfile() {
        if (currentUser == null) {
            showAlert("Error", "No user data loaded.", Alert.AlertType.ERROR);
            return;
        }

        String newUsername = usernameField.getText();
        String newEmail = emailField.getText();
        String newPassword = passwordField.getText();

        if (newUsername.isEmpty() || newEmail.isEmpty()) {
            showAlert("Error", "Username and Email cannot be empty.", Alert.AlertType.ERROR);
            return;
        }

        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);

        if (!newPassword.isEmpty()) {
            currentUser.setPasswordHash(newPassword);
        }

        boolean success = userService.updateUser(currentUser);

        if (success) {
            showAlert("Success", "Profile updated successfully!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Error", "Failed to update profile.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void backToMenu() {
        getSceneManager().switchScene("/ux/views/menu.fxml");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}

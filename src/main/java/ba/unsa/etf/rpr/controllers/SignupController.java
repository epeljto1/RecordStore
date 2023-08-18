package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignupController {
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;

    private final UserManager userManager = new UserManager();

    public void initialize() {
        Platform.runLater(() -> usernameField.requestFocus());

        usernameField.textProperty().addListener((observable, o, n) -> {
            if(n.trim().isEmpty()) setInvalidStyles(usernameField);
            else removeInvalidStyles(usernameField);
        });

        passwordField.textProperty().addListener((observable, o, n) -> {
            if(n.trim().isEmpty()) setInvalidStyles(passwordField);
            else removeInvalidStyles(passwordField);
        });

        confirmPasswordField.textProperty().addListener((observable, o, n) -> {
            if(n.trim().isEmpty() || !n.equals(passwordField.getText())) setInvalidStyles(confirmPasswordField);
            else removeInvalidStyles(confirmPasswordField);
        });
    }

    private void setInvalidStyles(TextField textField) {
        textField.getStyleClass().removeAll("default");
        textField.getStyleClass().add("invalid");
    }

    private void removeInvalidStyles(TextField textField) {
        textField.getStyleClass().removeAll("invalid");
        textField.getStyleClass().add("default");
    }
}

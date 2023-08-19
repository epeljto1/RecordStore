package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.business.UserManager;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Label errorMsgLabel;

    private final TabManager windowManager = new TabManager();
    private final UserManager userManager = new UserManager();

    public void initialize() {
        Platform.runLater(() -> usernameField.requestFocus()); // needed to set focus on username field

        usernameField.textProperty().addListener((observable, o, n) -> {
            if(n.trim().isEmpty()) setInvalidStyles(usernameField);
            else removeInvalidStyles(usernameField);
        });

        passwordField.textProperty().addListener((observable, o, n) -> {
            if(n.trim().isEmpty()) setInvalidStyles(passwordField);
            else removeInvalidStyles(passwordField);
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

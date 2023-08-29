package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import ba.unsa.etf.rpr.exceptions.UserException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;

/**
 * Controller for sign up page
 * @author Emina Peljto
 */

public class SignupController {
    public TextField usernameField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;
    public Label errorMsgLabel;

    private final UserManager userManager = new UserManager();
    private final TabManager tabManager = new TabManager();

    @FXML
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

    /**
     * Event handler for sign up action
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void signupAction(ActionEvent actionEvent) throws RecordStoreException {
        User user = new User();
        try {
            user.setUsername(usernameField.getText());
            user.setPassword(userManager.hashPassword(passwordField.getText()));

            userManager.validateSignUp(passwordField.getText(), confirmPasswordField.getText());
            userManager.add(user);
        } catch (UserException | RecordStoreException e) {
            if(e instanceof UserException) errorMsgLabel.setText(e.getMessage());
            else errorMsgLabel.setText("User already exists.");
            errorMsgLabel.setVisible(true);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Event handler for close action
     * @param actionEvent
     */
    public void closeAction(ActionEvent actionEvent) {
        tabManager.closeWindow(actionEvent);
    }

    /**
     * Event handler for log in action
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void goToLoginAction(ActionEvent actionEvent) throws RecordStoreException {
        tabManager.changeWindow("Login", "Log in", new LoginController(), actionEvent);
    }

    /**
     * Applies invalid styles to text field
     * @param textField
     */
    private void setInvalidStyles(TextField textField) {
        textField.getStyleClass().removeAll("default");
        textField.getStyleClass().add("invalid");
    }

    /**
     * Removes invalid styles from text field
     * @param textField
     */
    private void removeInvalidStyles(TextField textField) {
        textField.getStyleClass().removeAll("invalid");
        textField.getStyleClass().add("default");
    }
}

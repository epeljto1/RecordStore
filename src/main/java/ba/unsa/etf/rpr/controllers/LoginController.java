package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordStoreException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;

/**
 * Controller for log in page
 * @author Emina Peljto
 */

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Label errorMsgLabel;

    private final TabManager tabManager = new TabManager();
    private final UserManager userManager = new UserManager();

    @FXML
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

    /**
     * Event handler for log in action
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void loginAction(ActionEvent actionEvent) throws RecordStoreException
    {
        User user;
        try
        {
            user = userManager.getUser(usernameField.getText(),userManager.hashPassword(passwordField.getText()));
        }
        catch(RecordStoreException e)
        {
            errorMsgLabel.setText("The username or password is incorrect.");
            errorMsgLabel.setVisible(true);
            return;
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        tabManager.changeWindow("Home","Home", new HomeController(user.getUsername()),actionEvent);
    }

    /**
     * Event handler for sign up action
     * @param actionEvent
     * @throws RecordStoreException
     */
    public void goToSignupAction(ActionEvent actionEvent) throws RecordStoreException {
        tabManager.changeWindow("Signup", "Sign up", new SignupController(), actionEvent);
    }

    /**
     * Event handler for close action
     * @param actionEvent
     */
    public void closeAction(ActionEvent actionEvent) {
        tabManager.closeWindow(actionEvent);
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

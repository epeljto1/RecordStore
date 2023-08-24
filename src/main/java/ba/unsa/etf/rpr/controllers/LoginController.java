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

    public void loginAction(ActionEvent actionEvent) throws RecordStoreException
    {
        User user;
        try
        {
            user = userManager.getUser(usernameField.getText(),userManager.hashPassword(passwordField.getText()));
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }

        tabManager.changeWindow("Home","Home", new HomeController(user.getUsername()),actionEvent);
    }
    public void goToSignupAction(ActionEvent actionEvent) throws RecordStoreException {
        tabManager.changeWindow("Signup", "Sign up", new SignupController(), actionEvent);
    }
    public void closeAction(ActionEvent actionEvent) {
        tabManager.closeWindow(actionEvent);
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

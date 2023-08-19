package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TabManager;
import ba.unsa.etf.rpr.business.UserManager;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    public TextField usernameField;
    public PasswordField passwordField;
    public Label errorMsgLabel;

    private final TabManager windowManager = new TabManager();
    private final UserManager userManager = new UserManager();
}

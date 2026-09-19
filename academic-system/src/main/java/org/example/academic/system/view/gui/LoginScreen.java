package org.example.academic.system.view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.User;

import java.util.function.Consumer;

/**
 * Tela de login JavaFX (TUS-2407). A autenticacao e delegada ao
 * AuthenticationController (TUS-2414); a senha usa PasswordField e
 * nunca e exibida em texto plano (AC5).
 */
public class LoginScreen {

    private final AuthenticationController authenticationController;
    private final Consumer<User> onLoginSuccess;

    public LoginScreen(AuthenticationController authenticationController,
                       Consumer<User> onLoginSuccess) {
        this.authenticationController = authenticationController;
        this.onLoginSuccess = onLoginSuccess;
    }

    public Parent getView() {
        Label title = new Label("Academic System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Usuario");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Senha");

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setWrapText(true);

        Button loginButton = new Button("Entrar");
        loginButton.setDefaultButton(true);
        loginButton.setMaxWidth(Double.MAX_VALUE);
        loginButton.setOnAction(event -> {
            try {
                User user = authenticationController.authenticate(
                        usernameField.getText().trim(), passwordField.getText());
                onLoginSuccess.accept(user);
            } catch (AuthenticationException e) {
                errorLabel.setText(e.getMessage());
                passwordField.clear();
            }
        });

        VBox box = new VBox(10, title, usernameField, passwordField, loginButton, errorLabel);
        box.setPadding(new Insets(24));
        box.setAlignment(Pos.CENTER);
        return box;
    }
}

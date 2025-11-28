package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    // Tela de cadastro
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    // Lógica de login
    @FXML
    private void login() throws IOException {

        String email = emailField.getText();
        String senha = passwordField.getText();

        // Login cliente
        if (email.equals("luiz123@gmail.com") && senha.equals("1234567")) {
            App.setRoot("telaPrincipal-cliente"); // coloque o nome correto da sua tela
            return;
        }

        // Login barbeiro
        if (email.equals("luiz123@genbarber.com") && senha.equals("456")) {
            App.setRoot("telaPrincipal-barbeiro"); // coloque o nome correto da sua tela
            return;
        }

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro de Login");
        alert.setHeaderText("Credenciais incorretas");
        alert.setContentText("O e-mail ou a senha que você digitou estão incorretos.");
        alert.showAndWait();
    }
}

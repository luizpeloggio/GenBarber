package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import dao.ClienteDao;
import com.meuapp.model.Cliente;

public class PrimaryController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

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

        ClienteDao dao = new ClienteDao();
        
        //Solicita o banco de dados
        Cliente clienteLogado = dao.autenticar(email, senha);
        
        if(clienteLogado != null) {
        	//Sucesso
        	
        	//Salva na sessão
        	Sessao.setClienteLogado(clienteLogado);
        	
        	//Muda de tela
        	App.setRoot("telaPrincipal-cliente");
        } else {
        	//Erro
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Login Inválido");
        	alert.setHeaderText("Acesso Negado");
        	alert.setContentText("Email ou senha incorretos");
        	alert.showAndWait();
        }
    }
}

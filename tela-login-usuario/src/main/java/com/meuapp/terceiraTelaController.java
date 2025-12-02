package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import dao.ClienteDao;
import com.meuapp.model.Cliente;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class terceiraTelaController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField cpfField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField confirmPasswordField;

	// Método de Ação (chamado pelo botão "Cadastrar")
	@FXML
	private void telaPrincipalCliente() throws IOException {
		ClienteDao clienteDao = new ClienteDao();

		//Coleta e Validação
		String cpf = cpfField.getText().trim();
		String nome = nameField.getText().trim();
		String email = emailField.getText().trim();
		String telefone = phoneField.getText().trim();
		String senha = passwordField.getText();
		String confirmaSenha = confirmPasswordField.getText();

		if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || confirmaSenha.isEmpty()) {
			exibirAlerta("Erro de Validação", "Todos os campos (incluindo CPF) são obrigatórios.");
			return;
		}
		if (!senha.equals(confirmaSenha)) {
			exibirAlerta("Erro de Senha", "As senhas não coincidem.");
			return;
		}

		//Cria o objeto Modelo
		Cliente novoCliente = new Cliente();
		novoCliente.setCPF(cpf);
		novoCliente.setNome(nome);
		novoCliente.setEmail(email);
		novoCliente.setTelefone(telefone);
		novoCliente.setSenha(senha);

		//Persistência no banco de dados
		boolean sucesso = clienteDao.inserir(novoCliente);

		//Feedback e Navegação
		if (sucesso) {
			exibirAlerta("Sucesso", "Cliente " + nome + " cadastrado com êxito!");
			// Redireciona para a tela principal (lógica do App.setRoot)
			App.setRoot("telaPrincipal-cliente");
		} else {
			exibirAlerta("Erro", "Falha ao cadastrar o cliente. Verifique se o CPF já está em uso.");
		}
	}

	// Métodos auxiliares de navegação (já existentes)
	@FXML
	private void switchToSecondary() throws IOException {
		App.setRoot("secondary");
	}

	// Método para exibir Alertas
	private void exibirAlerta(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}
package com.meuapp;

import dao.AgendamentoDAO;
import com.meuapp.model.Agendamento;
import com.meuapp.model.Carrinho;
import com.meuapp.model.Servico;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Random;
import java.util.ResourceBundle;

public class finalizarPedido implements Initializable {

	@FXML
	private VBox listaCompras;
	@FXML
	private Label lblPagamento;
	@FXML
	private Label lblEndereco;
	@FXML
	private Label lblTotal;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		listaCompras.getChildren().clear();
		double total = 0;

		// 1. Carrega os itens do Carrinho (modelo em memória)
		if (Carrinho.getItens().isEmpty()) {
			// Pode adicionar uma mensagem de erro se o carrinho estiver vazio
			listaCompras.getChildren().add(new Label("Carrinho vazio! Adicione serviços primeiro."));
		} else {
			// 2. Calcula o total e exibe os itens na listaCompras VBox
			for (Servico s : Carrinho.getItens()) {
				Label item = new Label(s.getNome() + " - R$ " + String.format("%.2f", s.getPreco()));
				item.setStyle("-fx-font-size: 14px;");
				listaCompras.getChildren().add(item);
				total += s.getPreco();
			}
		}

		// 3. Atualiza os placeholders (que deveriam vir de outras telas)
		lblTotal.setText("R$ " + String.format("%.2f", total));
		lblPagamento.setText("Cartão de Crédito"); // Placeholder do Pagamento
		lblEndereco.setText("Rua Exemplo, 123 - Centro"); // Placeholder do Endereço
	}

	// Método de Ação (Chamado ao clicar no botão "Confirmar")
	@FXML
	private void ConfirmarPedido() throws IOException {

		//Verificação de usuario logado
		if (Sessao.getClienteLogado() == null) {
			exibirAlerta("Erro", "Sessão expirada. Faça login novamente.");
			App.setRoot("primary");
			return;
		}

		//Coleta e Preparação dos Dados do Carrinho
		if (Carrinho.getItens().isEmpty()) {
			exibirAlerta("Erro", "O carrinho está vazio. Adicione serviços antes de confirmar.");
			return;
		}

		double total = 0.0;
		StringBuilder servicos = new StringBuilder();

		for (Servico s : Carrinho.getItens()) {
			total += s.getPreco();
			servicos.append(s.getNome()).append(", ");
		}

		if (servicos.length() > 0) {
			servicos.setLength(servicos.length() - 2);
		}

		Agendamento novoAgendamento = new Agendamento();

		novoAgendamento.setCod_Agendamento(new Random().nextInt(1000000) + 1);

		//Dados do usuario logado
		novoAgendamento.setCPF(Sessao.getClienteLogado().getCpf());

		novoAgendamento.setCNPJ("123421");

		long agora = System.currentTimeMillis();
		novoAgendamento.setDate(new java.sql.Date(agora));
		novoAgendamento.setHorario(new java.sql.Time(agora));

		novoAgendamento.setTipo_Pagamento(lblPagamento.getText());
		novoAgendamento.setID_Promocao(0);

		novoAgendamento.setTipo_Servico(servicos.toString());

		novoAgendamento.setValor(total);

		AgendamentoDAO dao = new AgendamentoDAO();

		boolean sucesso = dao.inserir(novoAgendamento);

		if (sucesso) {
			Carrinho.limpar();
			exibirAlerta("Sucesso", "Agendamento confirmado com sucesso!");
			App.setRoot("telaPrincipal-cliente");
		} else {
			exibirAlerta("Erro", "Falha ao registrar. Verifique se o banco de dados está rodando.");
		}
	}

	private void exibirAlerta(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}

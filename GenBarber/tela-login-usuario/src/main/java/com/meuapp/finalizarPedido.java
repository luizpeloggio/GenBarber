package com.meuapp;

import com.meuapp.dao.AgendamentoDAO;
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

		// 1. Coleta e Preparação dos Dados do Carrinho
		double total = 0;
		StringBuilder servicos = new StringBuilder();

		for (Servico s : Carrinho.getItens()) {
			total += s.getPreco();
			servicos.append(s.getNome()).append(", ");
		}

		if (Carrinho.getItens().isEmpty()) {
			exibirAlerta("Erro", "O carrinho está vazio. Adicione serviços antes de confirmar.");
			return;
		}

		// Limpa a vírgula final
		servicos.setLength(servicos.length() - 2);

		// 2. Mapeamento Objeto (Agendamento) - POO
		Agendamento novoAgendamento = new Agendamento();

		// ** ID de Agendamento ** (Gerando um ID único aleatório)
		novoAgendamento.setCod_Agendamento(new Random().nextInt(1000000) + 1);

		// DADOS OBRIGATÓRIOS (Placeholders de teste)
		novoAgendamento.setCPF("12345678911"); // CPF do cliente (TESTE)
		novoAgendamento.setCNPJ("123421"); // CNPJ da Barbearia (TESTE)

		// Outros campos obrigatórios
		novoAgendamento.setDate(new Date(System.currentTimeMillis())); // Data de hoje (usando java.sql.Date)
		novoAgendamento.setHorario(new Time(System.currentTimeMillis())); // Hora atual (usando java.sql.Time)
		novoAgendamento.setTipo_Pagamento(lblPagamento.getText()); // Pega o texto do Label
		novoAgendamento.setID_Promocao(0); // Usa ID 0 para 'sem promoção'

		// Dados do Serviço
		novoAgendamento.setTipo_Servico(servicos.toString());
		novoAgendamento.setValor((float) total);

		// 3. Persistência (Chama o DAO - CRUD CREATE)
		AgendamentoDAO dao = new AgendamentoDAO();
		boolean sucesso = dao.inserir(novoAgendamento);

		// 4. Feedback e Limpeza
		if (sucesso) {
			Carrinho.limpar(); // Limpa o carrinho
			exibirAlerta("Sucesso", "Agendamento confirmado e salvo no banco de dados!");

			// Redireciona para a próxima tela
			App.setRoot("telaPrincipal-cliente");
		} else {
			exibirAlerta("Erro", "Falha ao registrar o agendamento no banco de dados. Verifique a conexão.");
		}
	}

	// Método auxiliar para exibir Alertas
	private void exibirAlerta(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}

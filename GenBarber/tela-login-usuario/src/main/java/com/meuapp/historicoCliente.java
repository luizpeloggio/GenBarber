package com.meuapp;

import com.meuapp.dao.AgendamentoDAO;
import com.meuapp.model.Agendamento;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.shape.Circle;

public class historicoCliente implements Initializable {

	@FXML
	private VBox containerContent; // VBox fx:id do FXML
	@FXML
	private ImageView fotoHistorico; // ImageView fx:id (existente no FXML)

	// O método initialize é o ponto de entrada da lógica, chamado ao carregar o
	// FXML
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		carregarHistorico();

		// Chama a lógica de UI existente para arredondar a imagem principal
		if (fotoHistorico != null) {
			tornarRedondo(fotoHistorico);
		}
	}

	// Lógica principal de Consulta (READ)
	public void carregarHistorico() {
		// CPF Fixo para Teste (Deve existir na tabela Usuario/Cliente!)
		String cpfClienteLogado = "12345678911";

		AgendamentoDAO dao = new AgendamentoDAO();
		// Chama o método DAO (JDBC) que executa o SELECT no banco
		List<Agendamento> agendamentos = dao.buscarPorCpf(cpfClienteLogado);

		containerContent.getChildren().clear(); // Limpa o VBox antes de popular

		if (agendamentos.isEmpty()) {
			containerContent.getChildren().add(new Label("Nenhum agendamento encontrado para o CPF " + cpfClienteLogado + "."));
			return;
		}

		// Popula a interface (Mapeamento POO -> View)
		for (Agendamento agendamento : agendamentos) {
			containerContent.getChildren().add(criarItemAgendamento(agendamento));
		}
	}

	// Cria o bloco visual para cada agendamento retornado
	private VBox criarItemAgendamento(Agendamento agendamento) {

		Label tipo = new Label("Serviço: " + agendamento.getTipo_Servico());
		tipo.setStyle("-fx-font-size: 16px; -fx-font-weight:bold;");

		Label detalhes = new Label("Cód: " + agendamento.getCod_Agendamento() + " | Data: " + agendamento.getData_()
				+ " | Hora: " + agendamento.getHorario() + " | Pgto: " + agendamento.getTipo_Pagamento()
				+ " | Valor: R$ " + String.format("%.2f", agendamento.getValor()));
		detalhes.setStyle("-fx-font-size: 12px;");

		VBox infoBox = new VBox(5, tipo, detalhes);
		HBox itemHBox = new HBox(10, infoBox);

		itemHBox.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f4f4f4;");

		return new VBox(10, itemHBox);
	}

	private void tornarRedondo(ImageView img) {
		// Garantindo que a imagem está carregada antes de tentar arredondar
		if (img.getImage() != null) {
			img.layoutBoundsProperty().addListener((obs, oldB, newB) -> {
				double radius = Math.min(newB.getWidth(), newB.getHeight()) / 2;
				img.setClip(new Circle(newB.getWidth() / 2, newB.getHeight() / 2, radius));
			});
		}
	}

	// Método original de navegação (existente)
	@FXML
	private void abrirConfiguracaoCliente(MouseEvent event) throws IOException {
		App.setRoot("configuracao-cliente");
	}

	@FXML
	private void abrirPerfilCliente(MouseEvent event) throws IOException {
		App.setRoot("perfil-cliente");
	}

	@FXML
	private void abrirComprasCliente(MouseEvent event) throws IOException {
		App.setRoot("compras-cliente");
	}

	@FXML
	private void abrirMenuCliente(MouseEvent event) throws IOException {
		App.setRoot("menu-cliente");
	}

	@FXML
	private void voltarPrincipalCliente(MouseEvent event) throws IOException {
		App.setRoot("telaPrincipal-cliente");
	}
}

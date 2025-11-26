package com.meuapp;

import java.io.IOException;

import com.meuapp.model.Agendamento;
import com.meuapp.model.Agendamentos;
import com.meuapp.model.Carrinho;
import com.meuapp.model.Rendimentos;
import com.meuapp.model.Servico;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class finalizarPedido {

    @FXML
    private VBox listaCompras;

    @FXML
    private Label lblPagamento;

    @FXML
    private Label lblEndereco;

    @FXML
    private Label lblTotal;

    @FXML
    public void initialize() {

        listaCompras.getChildren().clear();

        double total = 0;

        // Exibe os serviços adicionados ao carrinho
        for (Servico s : Carrinho.getItens()) {
            Label item = new Label(s.getNome() + " - R$ " + String.format("%.2f", s.getPreco()));
            item.setStyle("-fx-font-size: 14px;");
            listaCompras.getChildren().add(item);

            total += s.getPreco();
        }

        lblTotal.setText("R$ " + String.format("%.2f", total));

        // Placeholder (você pode substituir por dados reais no futuro)
        lblPagamento.setText("Cartão de Crédito");
        lblEndereco.setText("Rua Exemplo, 123 - Centro");
    }

    @FXML
    private void ConfirmarPedido() throws IOException {

        double total = 0;
        int qtdServicos = Carrinho.getItens().size();
        StringBuilder servicos = new StringBuilder();

        for (Servico s : Carrinho.getItens()) {
            total += s.getPreco();
            servicos.append(s.getNome()).append(", ");
        }

        if (servicos.length() > 2) {
            servicos.setLength(servicos.length() - 2); // remove última vírgula
        }

        // 👉 Salva rendimentos e atendimentos
        Rendimentos.adicionarRendimento(total);
        Rendimentos.adicionarAtendimentos(qtdServicos);

        // 👉 Cria e registra o agendamento do cliente
        Agendamento ag = new Agendamento("Luiz", servicos.toString(), total);
        Agendamentos.adicionar(ag);

        // Limpa o carrinho
        Carrinho.limpar();

        // Redireciona para área do barbeiro (agenda dele)
        App.setRoot("areaAgendamento");
    }
}

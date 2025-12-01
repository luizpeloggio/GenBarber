package com.meuapp;

import com.meuapp.model.Agendamento;
import com.meuapp.model.Agendamentos;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class areaAgendamento {

    @FXML
    private VBox containerAgendamentos;

    @FXML
    private void initialize() {

        containerAgendamentos.getChildren().clear();

        if (Agendamentos.getLista().isEmpty()) {
            containerAgendamentos.getChildren().add(
                    new Label("Por enquanto não há nenhum agendamento marcado"));
            return;
        }

        for (Agendamento a : Agendamentos.getLista()) {

            // ≡ Card principal
            VBox card = new VBox(10);
            card.setStyle("-fx-padding: 15; -fx-background-color: #f2f2f2; -fx-background-radius: 10;");

            // ≡ Texto do agendamento
            Label lbl = new Label(
                    "Cliente: " + a.getCPF() +
                            "\nServiço(s): " + a.getTipo_Servico() +
                            "\nValor: R$ " + String.format("%.2f", a.getValor()));

            lbl.setStyle("-fx-font-size: 14px;");

            // ≡ Botões Aceitar e Negar
            HBox botoes = new HBox(10);
            botoes.setStyle("-fx-padding: 10; -fx-alignment: center;");

            Button btnAceitar = new Button("Aceitar");
            btnAceitar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5 15;");
            btnAceitar.setOnAction(e -> aceitarAgendamento(a));

            Button btnNegar = new Button("Negar");
            btnNegar.setStyle("-fx-background-color: #E53935; -fx-text-fill: white; -fx-padding: 5 15;");
            btnNegar.setOnAction(e -> negarAgendamento(a));

            botoes.getChildren().addAll(btnAceitar, btnNegar);

            // adiciona tudo no card
            card.getChildren().addAll(lbl, botoes);

            // adiciona o card no container
            containerAgendamentos.getChildren().add(card);
        }
    }

    // ✔ Ao aceitar um pedido
    private void aceitarAgendamento(Agendamento a) {
        System.out.println("Agendamento aceito: " + a.getTipo_Servico());
        Agendamentos.getLista().remove(a);
        initialize(); // atualiza tela
    }

    // ✔ Ao negar um pedido
    private void negarAgendamento(Agendamento a) {
        System.out.println("Agendamento negado: " + a.getTipo_Servico());
        Agendamentos.getLista().remove(a);
        initialize(); // atualiza tela
    }

    @FXML
    private void irTelaPrincipalBarbeiro() throws Exception {
        App.setRoot("telaPrincipal-barbeiro");
    }

    @FXML
    private void irPerfilBarbeiro() throws Exception {
        App.setRoot("perfilBarbeiro");
    }

    @FXML
    private void irConfiguracaoBarbeiro() throws Exception {
        App.setRoot("configuracaoBarbeiro");
    }
}

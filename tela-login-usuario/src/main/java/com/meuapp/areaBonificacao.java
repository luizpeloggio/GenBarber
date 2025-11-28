package com.meuapp;

import com.meuapp.model.Bonificacao;
import com.meuapp.model.Bonificacoes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class areaBonificacao {

    @FXML
    private VBox listaBonificacoes;

    @FXML
    public void initialize() {
        atualizarLista();
    }

    private void atualizarLista() {
        listaBonificacoes.getChildren().clear();

        for (Bonificacao b : Bonificacoes.getLista()) {
            Label item = new Label(b.getNome() + " - R$ " + String.format("%.2f", b.getValor()));
            item.setStyle("-fx-font-size: 16px;");
            listaBonificacoes.getChildren().add(item);
        }
    }

    @FXML
    private void bonus2() throws Exception {
        App.setRoot("areaBonificacao2");
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

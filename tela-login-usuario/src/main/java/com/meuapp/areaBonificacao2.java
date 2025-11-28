package com.meuapp;

import com.meuapp.model.Bonificacao;
import com.meuapp.model.Bonificacoes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class areaBonificacao2 {
    @FXML
    private TextField nomeBonificacaoField;

    @FXML
    private TextField valorBonificacaoField;

    @FXML
    private void adicionarBonificacao() throws Exception {

        String nome = nomeBonificacaoField.getText();
        double valor = Double.parseDouble(valorBonificacaoField.getText());

        Bonificacao b = new Bonificacao(nome, valor);
        Bonificacoes.adicionar(b);

        // Após adicionar → volta para a tela principal de bonificações
        App.setRoot("areaBonificacao");
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

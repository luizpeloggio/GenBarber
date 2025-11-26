package com.meuapp;

import javafx.fxml.FXML;

public class telaPrincipalBarbeiro {
    @FXML
    private void irBonus() throws Exception {
        App.setRoot("areaBonificacao");

    }

    @FXML
    private void irAgendamento() throws Exception {
        App.setRoot("areaAgendamento");
    }

    @FXML
    private void irRendimentos() throws Exception {
        App.setRoot("areaRendimentos");
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

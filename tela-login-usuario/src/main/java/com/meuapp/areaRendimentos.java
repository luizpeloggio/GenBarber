package com.meuapp;

import com.meuapp.model.Rendimentos;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class areaRendimentos {

    @FXML
    private Label labelTotalRendimentos;

    @FXML
    private Label labelTotalAtendimentos;

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

    @FXML
    public void initialize() {
        labelTotalRendimentos.setText(
                "R$ " + String.format("%.2f", Rendimentos.getTotalRendimento()));
        labelTotalAtendimentos.setText(
                String.valueOf(Rendimentos.getTotalAtendimentos()));
    }
}

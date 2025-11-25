package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class menuCliente {

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

    @FXML
    private void abrirHistoricoCliente(MouseEvent event) throws IOException {
        App.setRoot("historico-cliente");
    }

    @FXML
    private void abrirFavoritosCliente(MouseEvent event) throws IOException {
        App.setRoot("favoritos-cliente");
    }

    @FXML
    private void abrirAgendamentoCliente(MouseEvent event) throws IOException {
        App.setRoot("agendamentoCliente");
    }
}
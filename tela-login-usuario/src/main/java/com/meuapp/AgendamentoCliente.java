package com.meuapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AgendamentoCliente {

    @FXML
    private TextField campoBusca;

    // =====================================
    // BOTÃO "VISITAR"
    // =====================================
    @FXML
    private void handleVisitarServico(ActionEvent event) throws IOException {
        System.out.println("Visitar serviço...");
        App.setRoot("agendamentoServicoCliente"); // Coloque o nome da tela correta
    }

    // =====================================
    // BOTÃO "BUSCAR"
    // =====================================
    @FXML
    private void handleBuscarBarbeiro(ActionEvent event) {
        String texto = campoBusca.getText();
        System.out.println("Buscando por: " + texto);

        // Aqui você pode implementar filtro, requisição, etc.
    }

    // =====================================
    // MÉTODOS DO MENU INFERIOR
    // =====================================

    @FXML
    private void voltarPrincipalCliente(MouseEvent event) throws IOException {
        App.setRoot("telaPrincipal-cliente");
    }

    @FXML
    private void abrirMenuCliente(MouseEvent event) throws IOException {
        App.setRoot("menu-cliente");
    }

    @FXML
    private void abrirComprasCliente(MouseEvent event) throws IOException {
        App.setRoot("compras-cliente");
    }

    @FXML
    private void abrirPerfilCliente(MouseEvent event) throws IOException {
        App.setRoot("perfil-cliente");
    }

    @FXML
    private void abrirConfiguracaoCliente(MouseEvent event) throws IOException {
        App.setRoot("configuracao-cliente");
    }
}

package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SecondaryController {

    @FXML
    private void abrirTelaBarbeiro(MouseEvent event) throws IOException {
        // Substitua "barbeiro-view" pelo nome do seu arquivo FXML para a tela do
        // barbeiro
        App.setRoot("barbeiro-cadastro");
    }

    @FXML
    private void abrirTelaCliente(MouseEvent event) throws IOException {
        App.setRoot("terceiraTela");
    }

    // Método original do botão
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
package com.meuapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class favoritosCliente {
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
    private VBox containerContent; // precisa ter fx:id no FXML

    @FXML
    private void removerFavorito(ActionEvent event) {
        // O botão que foi clicado
        Button btn = (Button) event.getSource();

        // O item que contém o botão (HBox)
        HBox item = (HBox) btn.getParent();

        // Remove da VBox principal
        containerContent.getChildren().remove(item);

        System.out.println("Item removido!");
    }

}

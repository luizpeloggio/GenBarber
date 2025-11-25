package com.meuapp;

import java.io.IOException;

import com.meuapp.model.Servico;
import com.meuapp.model.Carrinho;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class comprasCliente {

    @FXML
    private VBox listaCompras;

    @FXML
    private VBox msgVazioBox;

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
    private void irMenuCliente() throws IOException {
        App.setRoot("telaPrincipal-cliente");
    }

    // ============================================
    // POPULAR A LISTA COM OS ITENS DO CARRINHO
    // ============================================
    public void initialize() {

        listaCompras.getChildren().clear();

        if (Carrinho.getItens().isEmpty()) {
            msgVazioBox.setVisible(true);
            msgVazioBox.setManaged(true);
            return;
        }

        msgVazioBox.setVisible(false);
        msgVazioBox.setManaged(false);

        for (Servico s : Carrinho.getItens()) {
            listaCompras.getChildren().add(criarItemVisual(s));
        }
    }

    // ============================================
    // CRIAR BLOCO VISUAL PARA CADA SERVIÇO
    // ============================================
    private VBox criarItemVisual(Servico s) {

        ImageView img = new ImageView(
                new Image(getClass().getResourceAsStream("/com/meuapp/" + s.getImagem())));
        img.setFitWidth(80);
        img.setFitHeight(80);

        Label nome = new Label(s.getNome());
        nome.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label preco = new Label(String.format("R$ %.2f", s.getPreco()));
        preco.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");

        VBox box = new VBox(5, img, nome, preco);
        box.setStyle("""
                    -fx-padding: 10;
                    -fx-background-color: #ffffff;
                    -fx-border-color: #ccc;
                    -fx-border-width: 1;
                    -fx-background-radius: 10;
                    -fx-border-radius: 10;
                """);

        return box;
    }
}

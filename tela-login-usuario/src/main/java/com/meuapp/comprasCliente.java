package com.meuapp;

import java.io.IOException;

import com.meuapp.model.Servico;
import com.meuapp.model.Carrinho;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class comprasCliente {

    @FXML
    private VBox listaCompras;

    @FXML
    private VBox msgVazioBox;

    @FXML
    private Button btnFecharPedido;

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
    // initialize() ÚNICO — chama quando a tela abre
    // ============================================
    @FXML
    public void initialize() {

        listaCompras.getChildren().clear();

        boolean vazio = Carrinho.getItens().isEmpty();

        // Mostrar ou esconder mensagem de vazio
        msgVazioBox.setVisible(vazio);
        msgVazioBox.setManaged(vazio);

        // Mostrar ou esconder botão Fechar Pedido
        btnFecharPedido.setVisible(!vazio);
        btnFecharPedido.setManaged(!vazio);

        if (vazio)
            return;

        // Renderizar itens do carrinho
        for (Servico s : Carrinho.getItens()) {
            listaCompras.getChildren().add(criarItemVisual(s));
        }
    }

    // ============================================
    // CARD VISUAL DO ITEM
    // ============================================
    private VBox criarItemVisual(Servico s) {

        // ------------------ IMAGEM ------------------
        ImageView img = new ImageView(
                new Image(getClass().getResourceAsStream("/com/meuapp/images/" + s.getImagem())));
        img.setFitWidth(70);
        img.setFitHeight(70);

        // ------------------ TEXTOS ------------------
        Label nome = new Label(s.getNome());
        nome.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #222;");

        Label preco = new Label(String.format("R$ %.2f", s.getPreco()));
        preco.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

        VBox textoBox = new VBox(4, nome, preco);

        // ------------------ BOTÃO REMOVER ------------------
        Button btnRemover = new Button("Remover");
        btnRemover.setStyle("""
                    -fx-background-color: #ff4d4d;
                    -fx-text-fill: white;
                    -fx-padding: 6 14;
                    -fx-background-radius: 10;
                    -fx-font-size: 13px;
                """);

        btnRemover.setOnAction(e -> {
            Carrinho.remover(s);
            initialize(); // recarrega tela
        });

        // ------------------ LINHA PRINCIPAL ------------------
        HBox linha = new HBox(15, img, textoBox, btnRemover);
        linha.setStyle("-fx-alignment: center-left;");

        // ------------------ CARD ------------------
        VBox card = new VBox(linha);
        card.setStyle("""
                    -fx-padding: 14;
                    -fx-background-color: white;
                    -fx-background-radius: 14;
                    -fx-border-radius: 14;
                    -fx-border-color: #ddd;
                    -fx-border-width: 1;
                    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 6, 0, 0, 2);
                """);

        // ------------------ ANIMAÇÃO ------------------
        card.setOpacity(0);
        card.setTranslateY(10);

        FadeTransition fade = new FadeTransition(Duration.millis(250), card);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide = new TranslateTransition(Duration.millis(250), card);
        slide.setFromY(10);
        slide.setToY(0);

        fade.play();
        slide.play();

        return card;
    }

    @FXML
    private void fecharPedido(MouseEvent event) throws IOException {
        App.setRoot("finalizarPedido");
    }
}

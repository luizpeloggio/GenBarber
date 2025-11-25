package com.meuapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class historicoCliente {

    @FXML
    private VBox containerContent;

    @FXML
    private ImageView fotoHistorico;

    @FXML
    public void initialize() {
        // Arredonda a imagem principal
        tornarRedondo(fotoHistorico);

        // Arredonda todas as imagens dentro da lista (caso você adicione mais)
        arredondarTodasImagens();
    }

    private void tornarRedondo(ImageView img) {
        javafx.application.Platform.runLater(() -> {
            double largura = img.getFitWidth();
            double altura = img.getFitHeight();
            double raio = Math.min(largura, altura) / 2;

            javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(largura / 2, altura / 2, raio);

            img.setClip(clip);
        });
    }

    private void arredondarTodasImagens() {
        for (Node node : containerContent.getChildren()) {
            if (node instanceof HBox hbox) {
                for (Node filho : hbox.getChildren()) {
                    if (filho instanceof ImageView img) {
                        tornarRedondo(img);
                    }
                }
            }
        }
    }

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
}

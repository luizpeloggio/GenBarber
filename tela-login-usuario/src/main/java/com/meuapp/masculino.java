package com.meuapp;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class masculino {
    @FXML
    private void voltarPrincipalCliente(MouseEvent event) throws IOException {
        App.setRoot("telaPrincipal-cliente");
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
    private VBox listaBarbearias;

    @FXML
    public void initialize() {
        carregarBarbearias();
    }

    private void carregarBarbearias() {

        String[] nomes = {
                "Barbearia do Zé",
                "Barbearia Elite",
                "Old School Barber",
                "Barbearia Cavaleiros",
                "Barbearia MasterCuts",
                "Barbearia dos Amigos",
                "Barbearia Império",
                "Barbearia Top Line"
        };

        for (String nome : nomes) {
            listaBarbearias.getChildren().add(criarItemBarbearia(nome));
        }
    }

    private HBox criarItemBarbearia(String nome) {
        HBox item = new HBox();
        item.setSpacing(15);
        item.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10;");
        item.setPrefWidth(350);

        // CAMINHO CORRETO PARA IMAGENS NO RESOURCES
        Image imgFile = new Image(
                getClass().getResourceAsStream("/com/meuapp/salaoBeleza1.jpg"));

        ImageView img = new ImageView(imgFile);
        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setPreserveRatio(true);

        Label label = new Label(nome);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        item.getChildren().addAll(img, label);

        // Evento de clique
        item.setOnMouseClicked(e -> abrirBarbearia(nome));

        return item;
    }

    private void abrirBarbearia(String nome) {
        System.out.println("Abrindo: " + nome);
    }

}

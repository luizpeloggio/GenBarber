package com.meuapp;

import java.io.IOException;

import com.meuapp.model.Servico;
import com.meuapp.model.Carrinho;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class agendamentoServicoCliente {

    @FXML
    private VBox listaBarbearias;

    // ---------- Navegação ----------
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

    // ---------- Inicialização ----------
    @FXML
    private void initialize() {

        listaBarbearias.getChildren().clear();

        adicionarCard(
                "Corte Social",
                "67,80 R$",
                "/com/meuapp/images/corteSocial.png",
                () -> adicionarServico("Corte Social", 67.80, "corteSocial.png"));

        adicionarCard(
                "Corte Militar",
                "97,80 R$",
                "/com/meuapp/images/corteMilitar.png",
                () -> adicionarServico("Corte Militar", 97.80, "corteMilitar.png"));

        adicionarCard(
                "Buzz Cut",
                "17,80 R$",
                "/com/meuapp/images/buzzCut.png",
                () -> adicionarServico("Buzz Cut", 17.80, "buzzCut.png"));

        adicionarCard(
                "Fade",
                "37,80 R$",
                "/com/meuapp/images/fade.png",
                () -> adicionarServico("Fade", 37.80, "fade.png"));
    }

    // ---------- Função para criar cada card ----------
    private void adicionarCard(String nome, String preco, String imagem, Runnable acao) {

        VBox card = new VBox(12);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 12;"
                + "-fx-border-color: #ddd; -fx-border-radius: 12;");
        card.setMinWidth(320);

        // Linha superior (imagem + textos)
        HBox linha = new HBox(12);
        linha.setAlignment(Pos.CENTER_LEFT);

        Image imgFile = new Image(getClass().getResourceAsStream(imagem));
        ImageView img = new ImageView(imgFile);

        img.setFitWidth(90);
        img.setFitHeight(90);

        Circle clip = new Circle(45, 45, 45);
        img.setClip(clip);

        VBox info = new VBox(4);
        info.getChildren().addAll(
                new Label("Corte de cabelo: " + nome),
                new Label("Preço: " + preco));

        Pane espaco = new Pane();
        HBox.setHgrow(espaco, javafx.scene.layout.Priority.ALWAYS);

        linha.getChildren().addAll(img, info, espaco);

        // Botão de ação
        Button btn = new Button("Adicionar");
        btn.getStyleClass().add("buttonVisitar");
        btn.setOnAction(e -> acao.run());

        // Monta card
        card.getChildren().addAll(linha, btn);

        listaBarbearias.getChildren().add(card);
    }

    // ---------- Adicionar no carrinho ----------
    private void adicionarServico(String nome, double preco, String img) {
        Servico s = new Servico(nome, preco, img);
        Carrinho.adicionar(s);
        System.out.println("Carrinho agora tem: " + Carrinho.getItens().size());
    }
}

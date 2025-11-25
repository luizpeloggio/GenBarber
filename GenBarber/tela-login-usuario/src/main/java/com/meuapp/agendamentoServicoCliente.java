package com.meuapp;

import java.io.IOException;

import com.meuapp.model.Servico;
import com.meuapp.model.Carrinho;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;

public class agendamentoServicoCliente {

    @FXML
    private ImageView corteSocialImgView;
    @FXML
    private ImageView corteMilitarImgView;
    @FXML
    private ImageView buzzCutImgView;
    @FXML
    private ImageView fadeImgView;

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

    public void initialize() {
        makeRound(corteSocialImgView);
        makeRound(corteMilitarImgView);
        makeRound(buzzCutImgView);
        makeRound(fadeImgView);
    }

    private void makeRound(ImageView img) {
        img.layoutBoundsProperty().addListener((obs, oldB, newB) -> {
            double radius = Math.min(newB.getWidth(), newB.getHeight()) / 2;
            img.setClip(new Circle(newB.getWidth() / 2, newB.getHeight() / 2, radius));
        });
    }

    @FXML
    private void adicionarCorteSocial(MouseEvent event) {
        Servico s = new Servico("Corte Social", 67.80, "corteSocial.png");
        Carrinho.adicionar(s);
        System.out.println("Carrinho agora tem: " + Carrinho.getItens().size());

    }

    @FXML
    private void adicionarCorteMilitar(MouseEvent event) {
        Servico s = new Servico("Corte Militar", 97.80, "corteMilitar.png");
        Carrinho.adicionar(s);
        System.out.println("Carrinho agora tem: " + Carrinho.getItens().size());

    }

    @FXML
    private void adicionarBuzzCut(MouseEvent event) {
        Servico s = new Servico("Buzz Cut", 17.80, "buzzCut.png");
        Carrinho.adicionar(s);
        System.out.println("Carrinho agora tem: " + Carrinho.getItens().size());

    }

    @FXML
    private void adicionarFade(MouseEvent event) {
        Servico s = new Servico("Fade", 37.80, "fade.png");
        Carrinho.adicionar(s);
        System.out.println("Carrinho agora tem: " + Carrinho.getItens().size());

    }

}

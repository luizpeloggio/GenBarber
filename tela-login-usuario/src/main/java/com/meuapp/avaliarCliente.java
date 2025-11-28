package com.meuapp;

import java.io.IOException;

import com.meuapp.model.Avaliacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextArea;

import javafx.scene.control.Button;

public class avaliarCliente {

    @FXML
    private ImageView estrela1;
    @FXML
    private ImageView estrela2;
    @FXML
    private ImageView estrela3;
    @FXML
    private ImageView estrela4;
    @FXML
    private ImageView estrela5;
    @FXML
    private TextArea comentariosTextArea;
    @FXML
    private Button btnEnviar;

    private int avaliacao = 0;

    private Image starFilled;
    private Image starEmpty;

    @FXML
    public void initialize() {

        starFilled = new Image(getClass().getResourceAsStream("/com/meuapp/images/estrela-cheia.png"));
        starEmpty = new Image(getClass().getResourceAsStream("/com/meuapp/images/estrela-vazia.png"));

        estrela1.setOnMouseClicked(e -> setAvaliacao(1));
        estrela2.setOnMouseClicked(e -> setAvaliacao(2));
        estrela3.setOnMouseClicked(e -> setAvaliacao(3));
        estrela4.setOnMouseClicked(e -> setAvaliacao(4));
        estrela5.setOnMouseClicked(e -> setAvaliacao(5));

        btnEnviar.setOnAction(e -> enviarAvaliacao());
    }

    private void setAvaliacao(int nota) {
        avaliacao = nota;

        ImageView[] estrelas = { estrela1, estrela2, estrela3, estrela4, estrela5 };

        for (int i = 0; i < 5; i++) {
            if (i < nota)
                estrelas[i].setImage(starFilled);
            else
                estrelas[i].setImage(starEmpty);
        }
    }

    private void enviarAvaliacao() {
        String comentario = comentariosTextArea.getText();

        // Exemplo → cliente avaliando um barbeiro
        String avaliador = "Luiz";
        String avaliado = "Joao";

        Avaliacao a = new Avaliacao(avaliacao, comentario, avaliador, avaliado);
        BancoAvaliacoes.adicionar(a);

        System.out.println("Avaliação salva!");

        System.out.println("Avaliação: " + avaliacao + " estrelas");
        System.out.println("Comentário: " + comentariosTextArea.getText());
    }

    @FXML
    private void voltarTelaPrincipal(ActionEvent event) throws IOException {
        App.setRoot("telaPrincipal-cliente");
    }

    @FXML
    private void barbeiro(ActionEvent event) throws IOException {
        App.setRoot("telaPrincipal-barbeiro");
    }
}

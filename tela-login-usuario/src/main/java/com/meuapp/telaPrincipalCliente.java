package com.meuapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class telaPrincipalCliente implements Initializable {

    @FXML
    private TextField txtBuscaBarbeiro;

    @FXML
    private ImageView imgFeminino;
    @FXML
    private ImageView imgInfantil;
    @FXML
    private ImageView imgMasculino;
    @FXML
    private ImageView imgBarba;

    @FXML
    private void handleBuscarBarbeiro(javafx.event.ActionEvent event) {
        String busca = txtBuscaBarbeiro.getText();
        System.out.println("Buscando barbeiro: " + busca);
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
    private void abrirMasculino(MouseEvent event) throws IOException {
        App.setRoot("masculino");
    }

    /** Torna o ImageView circular imediatamente */
    private void tornarCircular(ImageView img) {
        double w = img.getFitWidth();
        double h = img.getFitHeight();
        double radius = Math.min(w, h) / 2;

        Circle clip = new Circle(w / 2, h / 2, radius);
        img.setClip(clip);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Apenas essas quatro imagens
        tornarCircular(imgFeminino);
        tornarCircular(imgInfantil);
        tornarCircular(imgMasculino);
        tornarCircular(imgBarba);
    }

}

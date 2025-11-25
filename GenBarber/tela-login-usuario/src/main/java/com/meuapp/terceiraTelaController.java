package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;

public class terceiraTelaController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void telaPrincipalCliente() throws IOException {
        App.setRoot("telaPrincipal-cliente");
    }
}
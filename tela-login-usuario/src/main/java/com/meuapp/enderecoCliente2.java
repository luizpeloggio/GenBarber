package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.meuapp.model.Endereco;
import com.meuapp.model.GerenciadorEnderecos;

public class enderecoCliente2 {

    @FXML
    private TextField fieldApelido;
    @FXML
    private TextField fieldCep;
    @FXML
    private TextField fieldLogradouro;
    @FXML
    private TextField fieldNumero;
    @FXML
    private TextField fieldComplemento;
    @FXML
    private TextField fieldBairro;
    @FXML
    private TextField fieldCidade;
    @FXML
    private ComboBox<String> comboEstado;
    @FXML
    private Button checkPrincipal;

    private boolean enderecoPrincipal = false;

    @FXML
    public void initialize() {
        System.out.println("Inicializando tela de cadastro de endereço...");
        carregarEstados();
        configurarValidacoes();
    }

    private void carregarEstados() {
        ObservableList<String> estados = FXCollections.observableArrayList(
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        comboEstado.setItems(estados);
    }

    private void configurarValidacoes() {
        // Formatação do CEP
        fieldCep.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 8 && !newValue.contains("-")) {
                fieldCep.setText(newValue.substring(0, 5) + "-" + newValue.substring(5));
            }
        });

        // Limitar número apenas a dígitos
        fieldNumero.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                fieldNumero.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void buscarCep() {
        String cep = fieldCep.getText().replace("-", "");
        if (cep.length() == 8) {
            System.out.println("Buscando CEP: " + cep);
            // Simulação de busca de CEP
            // Em uma aplicação real, você faria uma requisição à API dos Correios

            // Dados simulados para teste
            fieldLogradouro.setText("Rua das Flores");
            fieldBairro.setText("Jardim Primavera");
            fieldCidade.setText("São Paulo");
            comboEstado.setValue("SP");
        } else {
            System.out.println("CEP inválido");
        }
    }

    @FXML
    private void toggleEnderecoPrincipal() {
        enderecoPrincipal = !enderecoPrincipal;
        if (enderecoPrincipal) {
            checkPrincipal.setStyle(
                    "-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5 8; -fx-background-radius: 3; -fx-border-radius: 3;");
            checkPrincipal.setText("✓");
        } else {
            checkPrincipal.setStyle(
                    "-fx-background-color: #ecf0f1; -fx-text-fill: #7f8c8d; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5 8; -fx-background-radius: 3; -fx-border-radius: 3;");
            checkPrincipal.setText("✓");
        }
    }

    @FXML
    private void salvarEndereco() throws IOException {
        if (validarFormulario()) {
            Endereco novoEndereco = criarEnderecoFromForm();

            // Adiciona à lista global de endereços
            GerenciadorEnderecos.adicionarEndereco(novoEndereco);

            System.out.println("Endereço salvo: " + novoEndereco.getApelido());

            // Volta para a tela de endereços
            voltarParaEnderecos();
        }
    }

    private Endereco criarEnderecoFromForm() {
        String logradouroCompleto = fieldLogradouro.getText() + ", " + fieldNumero.getText();
        if (!fieldComplemento.getText().isEmpty()) {
            logradouroCompleto += " - " + fieldComplemento.getText();
        }

        return new Endereco(
                fieldApelido.getText(),
                logradouroCompleto,
                fieldBairro.getText(),
                fieldCidade.getText(),
                comboEstado.getValue(),
                fieldCep.getText(),
                enderecoPrincipal);
    }

    private boolean validarFormulario() {
        // Validação básica dos campos obrigatórios
        if (fieldApelido.getText().isEmpty()) {
            mostrarErro("Apelido é obrigatório");
            return false;
        }
        if (fieldCep.getText().isEmpty()) {
            mostrarErro("CEP é obrigatório");
            return false;
        }
        if (fieldLogradouro.getText().isEmpty()) {
            mostrarErro("Logradouro é obrigatório");
            return false;
        }
        if (fieldNumero.getText().isEmpty()) {
            mostrarErro("Número é obrigatório");
            return false;
        }
        if (fieldBairro.getText().isEmpty()) {
            mostrarErro("Bairro é obrigatório");
            return false;
        }
        if (fieldCidade.getText().isEmpty()) {
            mostrarErro("Cidade é obrigatória");
            return false;
        }
        if (comboEstado.getValue() == null) {
            mostrarErro("Estado é obrigatório");
            return false;
        }

        return true;
    }

    private void mostrarErro(String mensagem) {
        System.out.println("Erro de validação: " + mensagem);
        // Em uma aplicação real, você mostraria um alerta para o usuário
    }

    @FXML
    private void cancelar() throws IOException {
        voltarParaEnderecos();
    }

    private void voltarParaEnderecos() throws IOException {
        App.setRoot("endereco-cliente");
    }

    // AÇÕES DO MENU
    @FXML
    private void voltarPrincipalCliente() throws IOException {
        App.setRoot("telaPrincipal-cliente");
    }

    @FXML
    private void abrirMenuCliente() throws IOException {
        App.setRoot("menu-cliente");
    }

    @FXML
    private void abrirComprasCliente() throws IOException {
        App.setRoot("compras-cliente");
    }

    @FXML
    private void abrirPerfilCliente() throws IOException {
        App.setRoot("perfil-cliente");
    }

    @FXML
    private void abrirConfiguracaoCliente() throws IOException {
        App.setRoot("configuracao-cliente");
    }
}
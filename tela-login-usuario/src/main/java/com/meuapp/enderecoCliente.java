package com.meuapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import com.meuapp.model.Endereco;
import com.meuapp.model.GerenciadorEnderecos;

public class enderecoCliente {

    @FXML
    private VBox containerEnderecos;

    @FXML
    private VBox containerSemEnderecos;

    // Lista para armazenar os endereços
    private List<Endereco> enderecos = new ArrayList<>();

    @FXML
    public void initialize() {
        System.out.println("Inicializando tela de endereços...");
        carregarEnderecos();
        atualizarInterface();
    }

    private void carregarEnderecos() {
        enderecos.clear();

        // Primeiro tenta carregar do GerenciadorEnderecos
        try {
            enderecos.addAll(GerenciadorEnderecos.getEnderecos());
            System.out.println("Endereços carregados do Gerenciador: " + enderecos.size());
        } catch (Exception e) {
            System.out.println("Erro ao carregar do GerenciadorEnderecos: " + e.getMessage());
            // Fallback: carrega dados de exemplo
            carregarEnderecosExemplo();
        }

        // Se ainda estiver vazio, carrega exemplos
        if (enderecos.isEmpty()) {
            carregarEnderecosExemplo();
        }
    }

    private void carregarEnderecosExemplo() {
        // Dados de exemplo
        enderecos.add(new Endereco("Casa", "Rua Principal, 123", "Centro", "São Paulo", "SP", "01234-567", true));
        enderecos.add(
                new Endereco("Trabalho", "Av. Paulista, 1000", "Bela Vista", "São Paulo", "SP", "01310-100", false));
        System.out.println("Carregados endereços de exemplo: " + enderecos.size());
    }

    private void atualizarInterface() {
        containerEnderecos.getChildren().clear();

        if (enderecos.isEmpty()) {
            // Mostra mensagem de nenhum endereço
            containerSemEnderecos.setVisible(true);
            containerSemEnderecos.setManaged(true);
            System.out.println("Nenhum endereço cadastrado");
        } else {
            // Esconde mensagem de nenhum endereço
            containerSemEnderecos.setVisible(false);
            containerSemEnderecos.setManaged(false);

            // Adiciona cada endereço à interface
            for (Endereco endereco : enderecos) {
                VBox cardEndereco = criarCardEndereco(endereco);
                containerEnderecos.getChildren().add(cardEndereco);
            }
            System.out.println(enderecos.size() + " endereços exibidos");
        }
    }

    private VBox criarCardEndereco(Endereco endereco) {
        VBox card = new VBox(10);
        card.setStyle(
                "-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10; -fx-border-color: #ecf0f1; -fx-border-width: 1; -fx-border-radius: 10;");
        card.setMaxWidth(500);

        // Cabeçalho com apelido e botões de ação
        HBox cabecalho = new HBox();
        cabecalho.setAlignment(Pos.CENTER_LEFT);
        cabecalho.setSpacing(10);

        Label labelApelido = new Label(endereco.getApelido());
        labelApelido.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Tag de endereço principal
        if (endereco.isPrincipal()) {
            Label tagPrincipal = new Label("PRINCIPAL");
            tagPrincipal.setStyle(
                    "-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold; -fx-padding: 2 8; -fx-background-radius: 10;");
            cabecalho.getChildren().add(tagPrincipal);
        }

        cabecalho.getChildren().add(labelApelido);

        // Detalhes do endereço
        Label labelEndereco = new Label(endereco.getLogradouro());
        labelEndereco.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        Label labelBairroCidade = new Label(
                endereco.getBairro() + " - " + endereco.getCidade() + "/" + endereco.getEstado());
        labelBairroCidade.setStyle("-fx-font-size: 13px; -fx-text-fill: #7f8c8d;");

        Label labelCep = new Label("CEP: " + endereco.getCep());
        labelCep.setStyle("-fx-font-size: 12px; -fx-text-fill: #95a5a6;");

        // Botões de ação
        HBox botoesAcao = new HBox(10);
        botoesAcao.setAlignment(Pos.CENTER_RIGHT);

        Button btnEditar = new Button("Editar");
        btnEditar.setStyle(
                "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5 15; -fx-background-radius: 15;");
        btnEditar.setOnAction(e -> editarEndereco(endereco));

        Button btnExcluir = new Button("Excluir");
        btnExcluir.setStyle(
                "-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5 15; -fx-background-radius: 15;");
        btnExcluir.setOnAction(e -> excluirEndereco(endereco));

        Button btnDefinirPrincipal = new Button("Tornar Principal");
        btnDefinirPrincipal.setStyle(
                "-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 12px; -fx-padding: 5 15; -fx-background-radius: 15;");
        btnDefinirPrincipal.setOnAction(e -> definirComoPrincipal(endereco));

        // Esconde o botão "Tornar Principal" se já for o endereço principal
        if (endereco.isPrincipal()) {
            btnDefinirPrincipal.setVisible(false);
            btnDefinirPrincipal.setManaged(false);
        }

        botoesAcao.getChildren().addAll(btnDefinirPrincipal, btnEditar, btnExcluir);

        card.getChildren().addAll(cabecalho, labelEndereco, labelBairroCidade, labelCep, botoesAcao);

        return card;
    }

    @FXML
    private void adicionarNovoEndereco() throws IOException {
        System.out.println("Navegando para tela de cadastro de endereço...");
        // Navega para a tela de cadastro de novo endereço
        App.setRoot("endereco-cliente2");
    }

    private void editarEndereco(Endereco endereco) {
        // Implementar edição do endereço
        System.out.println("Editando endereço: " + endereco.getApelido());
        // App.setRoot("editar-endereco");
    }

    private void excluirEndereco(Endereco endereco) {
        System.out.println("Excluindo endereço: " + endereco.getApelido());

        // Remove do GerenciadorEnderecos se existir
        try {
            GerenciadorEnderecos.removerEndereco(endereco);
        } catch (Exception e) {
            System.out.println("Erro ao remover do GerenciadorEnderecos: " + e.getMessage());
        }

        // Remove da lista local
        enderecos.remove(endereco);
        atualizarInterface();
    }

    private void definirComoPrincipal(Endereco endereco) {
        System.out.println("Definindo endereço como principal: " + endereco.getApelido());

        // Atualiza no GerenciadorEnderecos se existir
        try {
            GerenciadorEnderecos.definirComoPrincipal(endereco);
        } catch (Exception e) {
            System.out.println("Erro ao definir principal no GerenciadorEnderecos: " + e.getMessage());
        }

        // Atualiza a lista local
        for (Endereco e : enderecos) {
            e.setPrincipal(e == endereco);
        }
        atualizarInterface();
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
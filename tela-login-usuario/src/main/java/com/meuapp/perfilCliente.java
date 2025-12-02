package com.meuapp;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.util.List;
import com.meuapp.model.Avaliacao;
import com.meuapp.model.CalculadoraAvaliacoes;

public class perfilCliente {

    @FXML
    private Label labelAvaliacao;

    @FXML
    private Label labelDetalhesAvaliacao;

    @FXML
    private HBox containerEstrelas;

    // Método que será chamado quando a tela for inicializada
    @FXML
    public void initialize() {
        carregarAvaliacoesCliente();
    }

    private void carregarAvaliacoesCliente() {
        // Aqui você precisa obter o nome do cliente logado
        // Vou assumir que você tem uma forma de obter isso
        String nomeCliente = obterNomeClienteLogado();

        // Obter avaliações feitas por barbeiros para este cliente
        List<Avaliacao> avaliacoes = BancoAvaliacoes.getAvaliacoesBarbeirosParaCliente(nomeCliente);
        double media = CalculadoraAvaliacoes.calcularMediaAvaliacoes(avaliacoes);
        int totalAvaliacoes = avaliacoes.size();

        // Atualizar a interface
        atualizarInterfaceAvaliacao(media, totalAvaliacoes);
    }

    private void atualizarInterfaceAvaliacao(double media, int totalAvaliacoes) {
        if (totalAvaliacoes == 0) {
            labelAvaliacao.setText("Nenhuma avaliação ainda");
            labelDetalhesAvaliacao.setText("Você ainda não foi avaliado pelos barbeiros");
        } else {
            labelAvaliacao.setText(String.format("%.1f ⭐", media));
            labelDetalhesAvaliacao.setText(totalAvaliacoes + " avaliação(ões) recebida(s)");

            // Criar estrelas visuais
            criarEstrelasVisuais(media);
        }
    }

    private void criarEstrelasVisuais(double media) {
        containerEstrelas.getChildren().clear();

        int estrelasCheias = (int) media;
        boolean temMeiaEstrela = (media - estrelasCheias) >= 0.5;

        // Adicionar estrelas cheias
        for (int i = 0; i < estrelasCheias; i++) {
            containerEstrelas.getChildren().add(criarEstrela("★", "#FFD700")); // Estrela amarela
        }

        // Adicionar meia estrela se necessário
        if (temMeiaEstrela) {
            containerEstrelas.getChildren().add(criarEstrela("★", "#FFD700")); // Ou você pode criar uma meia estrela
        }

        // Completar com estrelas vazias até 5
        int totalExibido = estrelasCheias + (temMeiaEstrela ? 1 : 0);
        for (int i = totalExibido; i < 5; i++) {
            containerEstrelas.getChildren().add(criarEstrela("☆", "#CCCCCC")); // Estrela cinza
        }
    }

    private Label criarEstrela(String texto, String cor) {
        Label estrela = new Label(texto);
        estrela.setStyle("-fx-font-size: 18px; -fx-text-fill: " + cor + ";");
        return estrela;
    }

    // Método para obter o nome do cliente logado - você precisa implementar
    // conforme sua lógica
    private String obterNomeClienteLogado() {
        // Exemplo: retornar de uma sessão ou usuário atual
        // return SessaoUsuario.getUsuarioLogado().getNome();
        return "Cliente Exemplo"; // Temporário - substitua pela sua lógica
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
    @FXML
    private void abrirMeuCadastro(MouseEvent event) throws IOException {
        App.setRoot("perfil-cliente-cadastro");
    }
}

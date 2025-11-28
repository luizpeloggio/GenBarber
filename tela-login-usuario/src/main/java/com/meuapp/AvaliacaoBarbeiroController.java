package com.meuapp;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import com.meuapp.model.Avaliacao;
import javafx.application.Platform;

public class AvaliacaoBarbeiroController {

    @FXML
    private Label labelNotaMedia;

    @FXML
    private Label labelTotalAvaliacoes;

    @FXML
    private HBox containerEstrelasPrincipais;

    @FXML
    private VBox containerDistribuicao;

    @FXML
    private VBox containerComentarios;

    @FXML
    public void initialize() {
        System.out.println("=== INICIALIZANDO TELA DE AVALIAÇÕES DO BARBEIRO ===");

        // TEMPORÁRIO: Adicionar dados de teste
        BancoAvaliacoes.adicionarDadosTeste();

        carregarAvaliacoesBarbeiro();
    }

    private void carregarAvaliacoesBarbeiro() {
        String nomeBarbeiro = obterNomeBarbeiroLogado();
        System.out.println("Buscando avaliações para: " + nomeBarbeiro);

        List<Avaliacao> avaliacoes = BancoAvaliacoes.getAvaliacoesClientesParaBarbeiro(nomeBarbeiro);
        System.out.println("Total de avaliações encontradas: " + avaliacoes.size());

        // Debug: mostrar cada avaliação
        for (Avaliacao av : avaliacoes) {
            System.out.println("Avaliação: Nota=" + av.getNota() + ", Comentário=" + av.getComentario() +
                    ", Avaliador=" + av.getAvaliador() + ", Avaliado=" + av.getAvaliado());
        }

        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada - exibindo estado vazio");
            exibirSemAvaliacoes();
        } else {
            double media = calcularMedia(avaliacoes);
            System.out.println("Média calculada: " + media);
            exibirResumoAvaliacao(media, avaliacoes.size());
            exibirDistribuicaoNotas(avaliacoes);
            exibirComentarios(avaliacoes);
        }
    }

    private double calcularMedia(List<Avaliacao> avaliacoes) {
        if (avaliacoes == null || avaliacoes.isEmpty()) {
            return 0.0;
        }

        double media = avaliacoes.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0.0);

        System.out.println("Média calculada para " + avaliacoes.size() + " avaliações: " + media);
        return media;
    }

    private void exibirResumoAvaliacao(double media, int totalAvaliacoes) {
        System.out.println("Exibindo resumo: média=" + media + ", total=" + totalAvaliacoes);

        // Atualizar a interface
        Platform.runLater(() -> {
            labelNotaMedia.setText(String.format("%.1f", media));
            labelTotalAvaliacoes.setText(totalAvaliacoes + " avaliação(ões)");

            // Criar estrelas principais
            criarEstrelasVisuais(containerEstrelasPrincipais, media, 24);
        });
    }

    private void exibirDistribuicaoNotas(List<Avaliacao> avaliacoes) {
        System.out.println("Exibindo distribuição de notas para " + avaliacoes.size() + " avaliações");

        // Agrupar por nota
        Map<Integer, Long> distribuicao = avaliacoes.stream()
                .collect(Collectors.groupingBy(Avaliacao::getNota, Collectors.counting()));

        int total = avaliacoes.size();
        System.out.println("Distribuição: " + distribuicao);

        Platform.runLater(() -> {
            containerDistribuicao.getChildren().clear(); // Limpar antes de adicionar

            for (int nota = 5; nota >= 1; nota--) {
                long quantidade = distribuicao.getOrDefault(nota, 0L);
                int porcentagem = total > 0 ? (int) ((quantidade * 100) / total) : 0;

                System.out.println("Nota " + nota + ": " + quantidade + " avaliações (" + porcentagem + "%)");

                HBox linhaDistribuicao = criarLinhaDistribuicao(nota, quantidade, porcentagem);
                containerDistribuicao.getChildren().add(linhaDistribuicao);
            }
        });
    }

    private HBox criarLinhaDistribuicao(int nota, long quantidade, int porcentagem) {
        HBox linha = new HBox(10);
        linha.setAlignment(Pos.CENTER_LEFT);

        // Label da nota
        Label labelNotaNumero = new Label(nota + " ");
        labelNotaNumero.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-min-width: 15;");

        // Estrelas da nota
        HBox estrelas = new HBox(2);
        for (int i = 0; i < 5; i++) {
            Label estrela = new Label(i < nota ? "★" : "☆");
            estrela.setStyle(i < nota ? "-fx-text-fill: #f39c12; -fx-font-size: 14px;"
                    : "-fx-text-fill: #ecf0f1; -fx-font-size: 14px;");
            estrelas.getChildren().add(estrela);
        }

        // Barra de progresso simulada
        HBox barraContainer = new HBox();
        barraContainer.setStyle("-fx-background-color: #ecf0f1; -fx-border-radius: 5; -fx-background-radius: 5;");
        barraContainer.setPrefWidth(150);
        barraContainer.setPrefHeight(8);

        HBox barraProgresso = new HBox();
        barraProgresso.setStyle("-fx-background-color: #f39c12; -fx-border-radius: 5; -fx-background-radius: 5;");
        barraProgresso.setPrefWidth(porcentagem * 1.5); // 150px máximo
        barraProgresso.setPrefHeight(8);
        barraContainer.getChildren().add(barraProgresso);

        // Labels
        Label labelPorcentagem = new Label(porcentagem + "%");
        labelPorcentagem.setStyle("-fx-font-size: 12px; -fx-min-width: 30;");

        Label labelQuantidade = new Label("(" + quantidade + ")");
        labelQuantidade.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d; -fx-min-width: 30;");

        linha.getChildren().addAll(labelNotaNumero, estrelas, barraContainer, labelPorcentagem, labelQuantidade);
        return linha;
    }

    private void exibirComentarios(List<Avaliacao> avaliacoes) {
        System.out.println("Exibindo comentários...");

        Platform.runLater(() -> {
            containerComentarios.getChildren().clear();

            int comentariosExibidos = 0;
            for (Avaliacao avaliacao : avaliacoes) {
                if (avaliacao.getComentario() != null && !avaliacao.getComentario().trim().isEmpty()) {
                    System.out.println("Adicionando comentário: " + avaliacao.getComentario());
                    VBox cardComentario = criarCardComentario(avaliacao);
                    containerComentarios.getChildren().add(cardComentario);
                    comentariosExibidos++;
                }
            }

            System.out.println("Total de comentários exibidos: " + comentariosExibidos);

            if (containerComentarios.getChildren().isEmpty()) {
                System.out.println("Nenhum comentário para exibir");
                Label semComentarios = new Label("Nenhum comentário disponível");
                semComentarios.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d; -fx-padding: 20;");
                containerComentarios.getChildren().add(semComentarios);
            }
        });
    }

    private VBox criarCardComentario(Avaliacao avaliacao) {
        VBox card = new VBox(10);
        card.setStyle(
                "-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10; -fx-border-color: #ecf0f1; -fx-border-width: 1; -fx-border-radius: 10;");
        card.setPrefWidth(600);
        card.setMaxWidth(600);

        // Cabeçalho com estrelas
        HBox cabecalho = new HBox(10);
        cabecalho.setAlignment(Pos.CENTER_LEFT);

        HBox estrelas = new HBox(2);
        for (int i = 0; i < 5; i++) {
            Label estrela = new Label(i < avaliacao.getNota() ? "★" : "☆");
            estrela.setStyle(i < avaliacao.getNota() ? "-fx-text-fill: #f39c12; -fx-font-size: 16px;"
                    : "-fx-text-fill: #ecf0f1; -fx-font-size: 16px;");
            estrelas.getChildren().add(estrela);
        }

        Label labelNota = new Label(avaliacao.getNota() + " estrelas");
        labelNota.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");

        cabecalho.getChildren().addAll(estrelas, labelNota);

        // Comentário
        Label comentario = new Label(avaliacao.getComentario());
        comentario.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50; -fx-wrap-text: true;");
        comentario.setWrapText(true);
        comentario.setMaxWidth(550);

        card.getChildren().addAll(cabecalho, comentario);

        return card;
    }

    private void criarEstrelasVisuais(HBox container, double media, int tamanhoFonte) {
        container.getChildren().clear();

        int estrelasCheias = (int) media;
        boolean temMeiaEstrela = (media - estrelasCheias) >= 0.5;

        System.out.println(
                "Criando estrelas visuais: média=" + media + ", cheias=" + estrelasCheias + ", meia=" + temMeiaEstrela);

        // Estrelas cheias
        for (int i = 0; i < estrelasCheias; i++) {
            container.getChildren().add(criarEstrela("★", "#f39c12", tamanhoFonte));
        }

        // Meia estrela
        if (temMeiaEstrela) {
            container.getChildren().add(criarEstrela("★", "#f39c12", tamanhoFonte));
        }

        // Estrelas vazias
        int totalExibido = estrelasCheias + (temMeiaEstrela ? 1 : 0);
        for (int i = totalExibido; i < 5; i++) {
            container.getChildren().add(criarEstrela("☆", "#ecf0f1", tamanhoFonte));
        }
    }

    private Label criarEstrela(String texto, String cor, int tamanhoFonte) {
        Label estrela = new Label(texto);
        estrela.setStyle("-fx-font-size: " + tamanhoFonte + "px; -fx-text-fill: " + cor + ";");
        return estrela;
    }

    private void exibirSemAvaliacoes() {
        Platform.runLater(() -> {
            labelNotaMedia.setText("0.0");
            labelTotalAvaliacoes.setText("Nenhuma avaliação");

            VBox mensagem = new VBox(10);
            mensagem.setAlignment(Pos.CENTER);
            mensagem.setStyle("-fx-padding: 40;");

            Label mensagemLabel = new Label("Você ainda não recebeu avaliações");
            mensagemLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");

            Label instrucaoLabel = new Label("Seus clientes poderão avaliar seus serviços após cada atendimento");
            instrucaoLabel.setStyle(
                    "-fx-font-size: 12px; -fx-text-fill: #bdc3c7; -fx-wrap-text: true; -fx-text-alignment: center;");
            instrucaoLabel.setWrapText(true);
            instrucaoLabel.setMaxWidth(300);

            mensagem.getChildren().addAll(mensagemLabel, instrucaoLabel);
            containerComentarios.getChildren().add(mensagem);

            // Limpar distribuição também
            containerDistribuicao.getChildren().clear();
            Label semDistribuicao = new Label("Nenhuma avaliação para distribuir");
            semDistribuicao.setStyle("-fx-font-size: 12px; -fx-text-fill: #bdc3c7;");
            containerDistribuicao.getChildren().add(semDistribuicao);
        });
    }

    private String obterNomeBarbeiroLogado() {
        // IMPORTANTE: Use o mesmo nome que está nas avaliações de teste
        return "João Silva";
    }

    // AÇÕES DO MENU
    @FXML
    private void voltarParaPerfil() throws IOException {
        App.setRoot("telaPrincipal-barbeiro");
    }

    @FXML
    private void irTelaPrincipalBarbeiro() throws Exception {
        App.setRoot("telaPrincipal-barbeiro");
    }

    @FXML
    private void irPerfilBarbeiro() throws Exception {
        App.setRoot("perfilBarbeiro");
    }

    @FXML
    private void irConfiguracaoBarbeiro() throws Exception {
        App.setRoot("configuracaoBarbeiro");
    }

    @FXML
    private void abrirAvaliacoes() throws IOException {
        App.setRoot("avaliacao-barbeiro");
    }
}
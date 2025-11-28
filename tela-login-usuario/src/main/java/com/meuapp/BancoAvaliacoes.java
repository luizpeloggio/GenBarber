package com.meuapp;

import com.meuapp.model.Avaliacao;
import com.meuapp.model.CalculadoraAvaliacoes; // Corrigi o import

import java.util.ArrayList;
import java.util.List;

public class BancoAvaliacoes {

    private static final List<Avaliacao> avaliacoes = new ArrayList<>();

    public static void adicionar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public static List<Avaliacao> getAvaliacoesPara(String usuario) {
        return avaliacoes.stream()
                .filter(a -> a.getAvaliado().equals(usuario))
                .toList();
    }

    // Novo método para obter apenas avaliações de clientes para barbeiros
    public static List<Avaliacao> getAvaliacoesClientesParaBarbeiro(String barbeiro) {
        System.out.println("Filtrando avaliações para barbeiro: " + barbeiro);

        List<Avaliacao> resultado = avaliacoes.stream()
                .filter(a -> a.getAvaliado().equals(barbeiro) &&
                        "cliente".equals(a.getAvaliador()))
                .toList();

        System.out.println("Resultado do filtro: " + resultado.size() + " avaliações");
        return resultado;
    }

    // Novo método para obter apenas avaliações de barbeiros para clientes
    public static List<Avaliacao> getAvaliacoesBarbeirosParaCliente(String cliente) {
        return avaliacoes.stream()
                .filter(a -> a.getAvaliado().equals(cliente) &&
                        "barbeiro".equals(a.getAvaliador()))
                .toList();
    }

    public static double getMediaAvaliacoes(String usuario) {
        List<Avaliacao> avaliacoesUsuario = getAvaliacoesPara(usuario);
        return CalculadoraAvaliacoes.calcularMediaAvaliacoes(avaliacoesUsuario);
    }

    public static int getTotalAvaliacoes(String usuario) {
        List<Avaliacao> avaliacoesUsuario = getAvaliacoesPara(usuario);
        return CalculadoraAvaliacoes.contarAvaliacoes(avaliacoesUsuario);
    }

    public static void adicionarDadosTeste() {
        System.out.println("Adicionando dados de teste...");

        // Limpar avaliações existentes
        avaliacoes.clear();

        // Adicionar avaliações de teste para o barbeiro "João Silva"
        adicionar(new Avaliacao(5, "Excelente serviço! Muito profissional.", "cliente", "João Silva"));
        adicionar(new Avaliacao(4, "Bom atendimento, mas atrasou um pouco.", "cliente", "João Silva"));
        adicionar(new Avaliacao(5, "Perfeito! Melhor barbeiro da cidade.", "cliente", "João Silva"));
        adicionar(new Avaliacao(3, "Serviço regular, poderia melhorar no acabamento.", "cliente", "João Silva"));
        adicionar(new Avaliacao(5, "Recomendo! Atendimento de qualidade.", "cliente", "João Silva"));

        System.out.println("Dados de teste adicionados: " + avaliacoes.size() + " avaliações");

        // Debug: mostrar todas as avaliações
        for (Avaliacao av : avaliacoes) {
            System.out.println(
                    "Avaliação no banco: " + av.getAvaliado() + " - " + av.getNota() + " - " + av.getComentario());
        }
    }
}

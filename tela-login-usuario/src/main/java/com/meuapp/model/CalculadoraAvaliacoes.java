package com.meuapp.model;

import java.util.List;

public class CalculadoraAvaliacoes {

    public static double calcularMediaAvaliacoes(List<Avaliacao> avaliacoes) {
        if (avaliacoes == null || avaliacoes.isEmpty()) {
            return 0.0;
        }

        int soma = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota();
        }

        return (double) soma / avaliacoes.size();
    }

    public static String formatarNota(double nota) {
        return String.format("%.1f", nota);
    }

    public static int contarAvaliacoes(List<Avaliacao> avaliacoes) {
        return avaliacoes != null ? avaliacoes.size() : 0;
    }
}

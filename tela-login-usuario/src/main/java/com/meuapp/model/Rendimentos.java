package com.meuapp.model;

public class Rendimentos {

    private static double totalRendimento = 0.0;
    private static int totalAtendimentos = 0;

    public static void adicionarRendimento(double valor) {
        totalRendimento += valor;
    }

    public static void adicionarAtendimentos(int quantidade) {
        totalAtendimentos += quantidade;
    }

    public static double getTotalRendimento() {
        return totalRendimento;
    }

    public static int getTotalAtendimentos() {
        return totalAtendimentos;
    }
}

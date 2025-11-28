package com.meuapp.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final List<Servico> itens = new ArrayList<>();

    // Adicionar item
    public static void adicionar(Servico s) {
        itens.add(s);
    }

    // Remover item específico
    public static void remover(Servico s) {
        itens.remove(s);
    }

    // Remover item pelo nome (opcional, útil se houver duplicados)
    public static void removerPorNome(String nome) {
        itens.removeIf(item -> item.getNome().equalsIgnoreCase(nome));
    }

    // Limpar tudo
    public static void limpar() {
        itens.clear();
    }

    // Retornar lista
    public static List<Servico> getItens() {
        return itens;
    }

    // Total do carrinho
    public static double getTotal() {
        double total = 0;
        for (Servico s : itens) {
            total += s.getPreco();
        }
        return total;
    }
}

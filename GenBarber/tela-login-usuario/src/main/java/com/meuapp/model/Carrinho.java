package com.meuapp.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final List<Servico> itens = new ArrayList<>();

    public static void adicionar(Servico s) {
        itens.add(s);
    }

    public static List<Servico> getItens() {
        return itens;
    }

    public static void limpar() {
        itens.clear();
    }
}

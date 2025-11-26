package com.meuapp.model;

import java.util.ArrayList;
import java.util.List;

public class Bonificacoes {

    private static final List<Bonificacao> lista = new ArrayList<>();

    public static void adicionar(Bonificacao b) {
        lista.add(b);
    }

    public static List<Bonificacao> getLista() {
        return lista;
    }
}

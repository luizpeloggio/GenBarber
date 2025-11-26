package com.meuapp.model;

import java.util.ArrayList;
import java.util.List;

public class Agendamentos {

    private static final List<Agendamento> lista = new ArrayList<>();

    public static void adicionar(Agendamento a) {
        lista.add(a);
    }

    public static List<Agendamento> getLista() {
        return lista;
    }
}

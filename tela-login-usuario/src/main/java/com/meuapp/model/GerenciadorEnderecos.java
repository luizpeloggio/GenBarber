package com.meuapp.model;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorEnderecos {
    private static List<Endereco> enderecos = new ArrayList<>();

    static {
        // Endereços iniciais para teste
        enderecos.add(new Endereco("Casa", "Rua Principal, 123", "Centro", "São Paulo", "SP", "01234-567", true));
        enderecos.add(
                new Endereco("Trabalho", "Av. Paulista, 1000", "Bela Vista", "São Paulo", "SP", "01310-100", false));
    }

    public static List<Endereco> getEnderecos() {
        return new ArrayList<>(enderecos); // Retorna uma cópia para evitar modificações externas
    }

    public static void adicionarEndereco(Endereco endereco) {
        // Se for definir como principal, remove a principalidade dos outros
        if (endereco.isPrincipal()) {
            for (Endereco e : enderecos) {
                e.setPrincipal(false);
            }
        }
        enderecos.add(endereco);
        System.out.println("Endereço adicionado: " + endereco.getApelido() + " - Total: " + enderecos.size());
    }

    public static void removerEndereco(Endereco endereco) {
        enderecos.remove(endereco);
    }

    public static void definirComoPrincipal(Endereco enderecoPrincipal) {
        for (Endereco endereco : enderecos) {
            endereco.setPrincipal(endereco == enderecoPrincipal);
        }
    }
}

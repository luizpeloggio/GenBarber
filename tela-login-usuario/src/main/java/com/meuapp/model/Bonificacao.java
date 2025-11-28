package com.meuapp.model;

public class Bonificacao {

    private String nome;
    private double valor;

    public Bonificacao(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
}

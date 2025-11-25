package com.meuapp.model;

public class Servico {
    private String nome;
    private double preco;
    private String imagem;

    public Servico(String nome, double preco, String imagem) {
        this.nome = nome;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagem() {
        return imagem;
    }
}

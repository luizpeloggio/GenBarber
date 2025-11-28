package com.meuapp.model;

public class Avaliacao {
    private int nota;
    private String comentario;
    private String avaliador; // cliente ou barbeiro
    private String avaliado; // barbeiro ou cliente

    public Avaliacao(int nota, String comentario, String avaliador, String avaliado) {
        this.nota = nota;
        this.comentario = comentario;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public String getAvaliador() {
        return avaliador;
    }

    public String getAvaliado() {
        return avaliado;
    }
}

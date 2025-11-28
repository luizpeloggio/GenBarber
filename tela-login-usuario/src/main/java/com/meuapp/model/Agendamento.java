package com.meuapp.model;

public class Agendamento {

    private String cliente;
    private String servico;
    private double valor;

    public Agendamento(String cliente, String servico, double valor) {
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
    }

    public String getCliente() {
        return cliente;
    }

    public String getServico() {
        return servico;
    }

    public double getValor() {
        return valor;
    }
}

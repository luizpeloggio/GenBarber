package com.meuapp.model;

public class Cliente {
	private String CPF; // Chave primaria
	private String Nome;
	private String Email;
	private String Telefone;

	// Construto Vazio
	public Cliente() {
	}

	// Construtor com todos os campos (util)
	public Cliente(String cpf, String nome, String email, String telefone) {
		this.CPF = cpf;
		this.Nome = nome;
		this.Email = email;
		this.Telefone = telefone;
	}

	// Metodos Getters/Setters (encapsulamento)
	// Acesso de atributos privados
	public void setCPF(String cpf) {
		this.CPF = cpf;
	}

	public String getCpf() {
		return CPF;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getNome() {
		return Nome;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getEmail() {
		return Email;
	}

	public void setTelefone(String telefone) {
		this.Telefone = telefone;
	}

	public String getTelefone() {
		return Telefone;
	}
}

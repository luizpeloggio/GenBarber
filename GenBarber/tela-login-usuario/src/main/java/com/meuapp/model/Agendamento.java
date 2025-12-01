 package com.meuapp.model;

import java.sql.Date;
import java.sql.Time;

public class Agendamento {
	private int Cod_Agendamento;
	private Date Data_;
	private Time Horario;
	private String Tipo_Pagamento;
	private String CNPJ;
	private int ID_Promocao;
	private String Tipo_Servico; // Tipo (do Serviço)
	private float Valor; //Valor (do Serviço/Agendamento)
	private String CPF;
	
	//Construtor Default
	public Agendamento(){
	}
	
	public void setCod_Agendamento(int Cod_Agendamento) {
		this.Cod_Agendamento = Cod_Agendamento;
	}
	public int getCod_Agendamento() {
		return Cod_Agendamento;
	}
	public void setDate(Date Data_) {
		this.Data_ = Data_;
	}
	public Date getData_() {
		return Data_;
	}
	public void setHorario(Time Horario) {
		this.Horario = Horario; 
	}
	public Time getHorario() {
		return Horario;
	}
	public void setTipo_Pagamento(String Tipo_Pagamento) {
		this.Tipo_Pagamento = Tipo_Pagamento;
	}
	public String getTipo_Pagamento() {
		return Tipo_Pagamento;
	}
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setID_Promocao(int ID_Promocao) {
		this.ID_Promocao = ID_Promocao;
	}
	public int getID_Promocao() {
		return ID_Promocao;
	}
	public void setTipo_Servico(String Tipo_Servico) {
		this.Tipo_Servico = Tipo_Servico;
	}
	public String getTipo_Servico() {
		return Tipo_Servico;
	}
	public void setValor(float Valor) {
		this.Valor = Valor;
	}
	public float getValor() {
		return Valor;
	}
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	public String getCPF() {
		return CPF;
	}
}

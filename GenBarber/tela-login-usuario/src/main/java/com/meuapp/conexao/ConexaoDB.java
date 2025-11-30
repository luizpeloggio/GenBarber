package com.meuapp.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
	// Constantes de conexao com o banco de dados
	private static final String url = "jdbc:mysql://localhost:3306/GenBarber_Banco?useTimezone=true&serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String senha = "BAV99Je3Z.RAefP";

	// Tenta estabelecer uma nova conexao com o banco
	// Retorna um objeto do tipo Connection, ou null se a conexao falhar
	@SuppressWarnings("exports")
	public static Connection conectar() {
		try {
			// Tenta estabelecer a conexao
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException e) {
			// Trata as excecoes (ex: senha errada, banco offline)
			System.err.println("Erro ao ao conectar ao banco de dados");
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("exports")
	public static void fecharConexao(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar a conexao: " + e.getMessage());
			}
		}
	}

}

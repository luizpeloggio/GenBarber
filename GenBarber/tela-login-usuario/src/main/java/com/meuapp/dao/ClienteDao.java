package com.meuapp.dao;

import com.meuapp.conexao.ConexaoDB;
import com.meuapp.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {
	public boolean inserir(Cliente cliente) {
		//SQL para a tabela USUARIO
		String sqlUsuario = "INSERT INTO Usuario (CPF, Nome, Email, Telefone) VALUES (?, ?, ?, ?)";
		//SQL para a tabela CLIENTE
		String sqlCliente = "INSERT INTO Cliente (CPF) VALUES (?)";

		Connection conexao = null;
		PreparedStatement stmtUsuario = null;
		PreparedStatement stmtCliente = null;

		try {
			conexao = ConexaoDB.conectar();
			conexao.setAutoCommit(false);

			// Passo A: Inserir em Usuario
			stmtUsuario = conexao.prepareStatement(sqlUsuario);
			stmtUsuario.setString(1, cliente.getCpf());
			stmtUsuario.setString(2, cliente.getNome());
			stmtUsuario.setString(3, cliente.getEmail());
			stmtUsuario.setString(4, String.valueOf(cliente.getTelefone()));
			stmtUsuario.executeUpdate();

			// Passo B: Inserir em Cliente
			stmtCliente = conexao.prepareStatement(sqlCliente);
			stmtCliente.setString(1, cliente.getCpf());
			stmtCliente.executeUpdate();

			conexao.commit();
			return true;

		} catch (SQLException e) {
			System.err.println("Erro ao inserir cliente: " + e.getMessage());
			try {
				if (conexao != null)
					conexao.rollback();
			} catch (SQLException ex) {
				System.err.println("Erro ao desfazer transação: " + ex.getMessage());
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmtUsuario != null)
					stmtUsuario.close();
				if (stmtCliente != null)
					stmtCliente.close();
				ConexaoDB.fecharConexao(conexao);
			} catch (SQLException e) {
				System.err.println("Erro ao fechar recursos: " + e.getMessage());
			}
		}
	}

	public boolean atualizar(Cliente cliente) {
		String sql = "UPDATE Usuario SET Nome = ?, Email = ?, Telefone = ? WHERE CPF = ?";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {
			conexao = ConexaoDB.conectar();
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, String.valueOf(cliente.getTelefone()));
			stmt.setString(4, cliente.getCpf());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar cliente: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				ConexaoDB.fecharConexao(conexao);
			} catch (SQLException e) {
				System.err.println("Erro ao fechar recursos: " + e.getMessage());
			}
		}
	}

	public boolean excluir(String cpf) {
		String sqlCliente = "DELETE FROM Cliente WHERE CPF = ?";
		String sqlUsuario = "DELETE FROM Usuario WHERE CPF = ?";

		Connection conexao = null;
		PreparedStatement stmtCliente = null;
		PreparedStatement stmtUsuario = null;
		boolean sucesso = false;

		try {
			conexao = ConexaoDB.conectar();
			conexao.setAutoCommit(false);

			stmtCliente = conexao.prepareStatement(sqlCliente);
			stmtCliente.setString(1, cpf);
			stmtCliente.executeUpdate();

			stmtUsuario = conexao.prepareStatement(sqlUsuario);
			stmtUsuario.setString(1, cpf);

			if (stmtUsuario.executeUpdate() > 0) {
				sucesso = true;
			}

			conexao.commit();
			return sucesso;

		} catch (SQLException e) {
			System.err.println("Erro ao excluir cliente: " + e.getMessage());
			try {
				if (conexao != null)
					conexao.rollback();
			} catch (SQLException ex) {
			}
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmtCliente != null)
					stmtCliente.close();
				if (stmtUsuario != null)
					stmtUsuario.close();
				ConexaoDB.fecharConexao(conexao);
			} catch (SQLException e) {
				System.err.println("Erro ao fechar recursos: " + e.getMessage());
			}
		}
	}
}

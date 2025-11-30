package com.meuapp.dao;

import com.meuapp.conexao.ConexaoDB;
import com.meuapp.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao {
	// Create - C
	/**
	 * Insere um novo cliente no banco de dados
	 * 
	 * @param cliente O objeto cliente a ser salvo
	 * @return true se a insercao for bem sucedida, false se nao for bem sucedida
	 */
	public boolean inserir(com.meuapp.model.Cliente cliente) {
		String sql = "INSERT INTO Cliente (CPF, Nome, Email, Telefone) VALUES(?, ?, ?, ?)";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {
			// Abrir a conexao com o banco de dados
			conexao = ConexaoDB.conectar();
			// Preparando o statement
			stmt = conexao.prepareStatement(sql);
			// Setando os valores nos espaços reservados (?)
			stmt.setString(1, cliente.getCpf()); // Primeiro '?' é o CPF
			stmt.setString(2, cliente.getNome()); // Segundo '?' é o nome
			stmt.setString(3, cliente.getEmail()); // Terceiro '?' é o email
			stmt.setString(4, cliente.getTelefone()); // Quarto '?' é o telefone

			// Executar o comando
			int num_linhasAfetadas = stmt.executeUpdate();

			// Se pelo menos uma linha tiver sido inserida, o método retorna true
			return num_linhasAfetadas > 0;
		} catch (SQLException e) {
			System.err.println("Erro ao inserir o novo cliente: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			// Finalizar o statemente e a conexao com o banco de dados
			// Se ocorrer o erro eles serão fechados
			try {
				if (stmt != null) {
					stmt.close();
				}
				ConexaoDB.fecharConexao(conexao);
			} catch (SQLException e) {
				System.err.println("Erro ao fechar os recursos: " + e.getMessage());
			}
		}
	}

	// Update - U
	/**
	 * Atualiza um cliente existente no banco de dados, utilizando o CPF como chave.
	 * 
	 * @param cliente O objeto Cliente com os dados atualizados.
	 * @return true se a atualização for bem-sucedida.
	 */
	public boolean atualizar(com.meuapp.model.Cliente cliente) {
		// SQL de UPDATE: CPF é a chave, os demais campos são alterados
		String sql = "UPDATE Cliente SET Nome = ?, Email = ?, Telefone = ? WHERE cpf = ?";

		Connection conexao = null;
		PreparedStatement stmt = null;

		try {
			conexao = com.meuapp.conexao.ConexaoDB.conectar();
			stmt = conexao.prepareStatement(sql);

			//Setar os novos valores (Nome, Email, Telefone)
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getTelefone());

			//Setar o valor do WHERE (CPF) - Chave primaria
			stmt.setString(4, cliente.getCpf());

			int linhasAfetadas = stmt.executeUpdate();
			return linhasAfetadas > 0;

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar cliente: " + e.getMessage());
			e.printStackTrace();
			return false;
		} finally {
			//Fechar recursos
			try {
				if (stmt != null)
					stmt.close();
				com.meuapp.conexao.ConexaoDB.fecharConexao(conexao);
			} catch (SQLException e) {
				System.err.println("Erro ao fechar recursos: " + e.getMessage());
			}
		}
	}
	/**
     * Remove um cliente do banco de dados pelo CPF (Delete).
     * @param cpf O CPF (chave primária) do cliente a ser removido.
     * @return true se a remoção for bem-sucedida.
     */
    public boolean excluir(String cpf) {
        // SQL de DELETE
        String sql = "DELETE FROM Cliente WHERE cpf = ?"; 
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = com.meuapp.conexao.ConexaoDB.conectar();
            stmt = conexao.prepareStatement(sql);
            
            //Setar o valor do WHERE (CPF)
            stmt.setString(1, cpf); 

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            //Fechar recursos
            try {
                if (stmt != null) 
                	stmt.close();
                com.meuapp.conexao.ConexaoDB.fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }
}

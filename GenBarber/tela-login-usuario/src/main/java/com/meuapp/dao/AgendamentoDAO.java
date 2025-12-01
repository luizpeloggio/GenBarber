package com.meuapp.dao;

import com.meuapp.conexao.ConexaoDB;
import com.meuapp.model.Agendamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {
	// SQLs Corrigidas para 9 campos na tabela Agendamento (incluindo Tipo e Valor)
	private static final String INSERT_AGENDAMENTO_SQL = "INSERT INTO Agendamento (Cod_Agendamento, Data_, Horario, Tipo_Pagamento, CNPJ, ID_Promocao, CPF, Tipo, Valor) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_AGENDAMENTO_SQL = "UPDATE Agendamento SET Data_ = ?, Horario = ?, Tipo_Pagamento = ?, ID_Promocao = ?, Tipo = ?, Valor = ? WHERE Cod_Agendamento = ?";

	private static final String DELETE_AGENDAMENTO_SQL = "DELETE FROM Agendamento WHERE Cod_Agendamento = ?";

	// C - CREATE (Versão Simplificada em 1 Passo)
	public boolean inserir(Agendamento agendamento) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		try {
			conexao = ConexaoDB.conectar();
			stmt = conexao.prepareStatement(INSERT_AGENDAMENTO_SQL);

			// Mapeamento Objeto POO -> Banco (9 parâmetros)
			stmt.setInt(1, agendamento.getCod_Agendamento()); // 1. Cod_Agendamento (Você deve gerar o ID)
			stmt.setDate(2, agendamento.getData_()); // 2. Data_
			stmt.setTime(3, agendamento.getHorario()); // 3. Horario
			stmt.setString(4, agendamento.getTipo_Pagamento()); // 4. Tipo_Pagamento
			stmt.setString(5, agendamento.getCNPJ()); // 5. CNPJ

			if (agendamento.getID_Promocao() != 0) {
				stmt.setInt(6, agendamento.getID_Promocao());
			} else {
				stmt.setInt(6, 0); // Usar ID de Promoção 0, conforme dados de exemplo.
			}

			stmt.setString(7, agendamento.getCPF()); // 7. CPF
			stmt.setString(8, agendamento.getTipo_Servico()); // 8. Tipo (Serviço)
			stmt.setFloat(9, agendamento.getValor()); // 9. Valor

			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Erro ao inserir agendamento: " + e.getMessage());
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

	// R - READ (Consulta para o Histórico)
	public List<Agendamento> buscarPorCpf(String cpf) {
		// Seleciona todos os campos da tabela Agendamento (agora que ela tem Tipo e
		// Valor)
		String sql = "SELECT * FROM Agendamento WHERE CPF = ? ORDER BY Data_ DESC, Horario DESC";

		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Agendamento> agendamentos = new ArrayList<>();

		try {
			conexao = ConexaoDB.conectar();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cpf);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Agendamento agendamento = new Agendamento();
				// Mapeamento DB -> Objeto POO
				agendamento.setCod_Agendamento(rs.getInt("Cod_Agendamento"));
				agendamento.setDate(rs.getDate("Data_"));
				agendamento.setHorario(rs.getTime("Horario"));
				agendamento.setTipo_Pagamento(rs.getString("Tipo_Pagamento"));
				agendamento.setCNPJ(rs.getString("CNPJ"));
				agendamento.setID_Promocao(rs.getInt("ID_Promocao"));
				agendamento.setCPF(rs.getString("CPF"));
				agendamento.setTipo_Servico(rs.getString("Tipo")); // Novo campo
				agendamento.setValor(rs.getFloat("Valor")); // Novo campo

				agendamentos.add(agendamento);
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar agendamentos: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				ConexaoDB.fecharConexao(conexao);
			} catch (SQLException e) {
				System.err.println("Erro ao fechar recursos: " + e.getMessage());
			}
		}
		return agendamentos;
	}

	// U - UPDATE
	public boolean atualizar(Agendamento agendamento) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		try {
			conexao = ConexaoDB.conectar();
			stmt = conexao.prepareStatement(UPDATE_AGENDAMENTO_SQL);

			// 1. Setar os novos valores (Data, Horario, Pagamento, ID_Promocao, Tipo,
			// Valor)
			stmt.setDate(1, agendamento.getData_());
			stmt.setTime(2, agendamento.getHorario());
			stmt.setString(3, agendamento.getTipo_Pagamento());

			if (agendamento.getID_Promocao() != 0) {
				stmt.setInt(4, agendamento.getID_Promocao());
			} else {
				stmt.setInt(4, 0);
			}

			stmt.setString(5, agendamento.getTipo_Servico());
			stmt.setFloat(6, agendamento.getValor());

			// 2. WHERE (PK)
			stmt.setInt(7, agendamento.getCod_Agendamento());

			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Erro ao atualizar agendamento: " + e.getMessage());
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

	// D - DELETE
	public boolean excluir(int codAgendamento) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		try {
			conexao = ConexaoDB.conectar();
			stmt = conexao.prepareStatement(DELETE_AGENDAMENTO_SQL);
			stmt.setInt(1, codAgendamento);
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println("Erro ao excluir agendamento: " + e.getMessage());
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
}

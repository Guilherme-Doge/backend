package senai.repo.equipamento;

import senai.database.Conexao;
import senai.model.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepoImpl implements EquipamentoRepo {

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        String sql = """
                    INSERT INTO Equipamento (nome, numero_serie, fornecedor_id)
                    VALUES (?, ?, ?);
                    """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getFornecedorId());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    equipamento.setId(rs.getInt(1));
                }
            }
        }
        return equipamento;
    }

    @Override
    public Equipamento buscarPorId(int id) throws SQLException {
        // Correção: Faltava o 'FROM Equipamento' e havia erro de digitação em 'fornecedor_id'
        String sql = """
                SELECT id, nome, numero_serie, fornecedor_id
                FROM Equipamento
                WHERE id = ?""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Correção: O parâmetro id não estava sendo passado

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Equipamento(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero_serie"),
                            rs.getInt("fornecedor_id"));
                } else {
                    throw new RuntimeException("Id do Equipamento não encontrado!");
                }
            }
        }
    }

    @Override
    public List<Equipamento> buscarPorFornecedorId(int fornecedorId) throws SQLException {
        List<Equipamento> equipamentos = new ArrayList<>();

        String sql = """
                SELECT id, nome, numero_serie, fornecedor_id
                FROM Equipamento
                WHERE fornecedor_id = ?""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, fornecedorId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    equipamentos.add(new Equipamento(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("numero_serie"),
                            rs.getInt("fornecedor_id")));
                }
            }
        }
        return equipamentos;
    }

    @Override
    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {
        String sql = """
                UPDATE Equipamento
                SET nome = ?, numero_serie = ?, fornecedor_id = ?
                WHERE id = ?""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getFornecedorId());
            stmt.setInt(4, equipamento.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletarEquipamento(int id) throws SQLException {
        String sql = "DELETE FROM Equipamento WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
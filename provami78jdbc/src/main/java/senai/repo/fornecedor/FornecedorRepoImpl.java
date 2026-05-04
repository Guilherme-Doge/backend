package senai.repo.fornecedor;

import senai.database.Conexao;
import senai.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorRepoImpl implements FornecedorRepo {
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException {
        String sql = """
                INSERT INTO 
                Fornecedor(
                    nome,
                    cnpj)
                VALUES (?,?);
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                fornecedor.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fornecedor;
    }

    @Override
    public Fornecedor buscarPorId(int id) throws SQLException {
        String sql = """
                SELECT nome, cnpj
                FROM Fornecedor
                WHERE id = ?;""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Fornecedor(id,
                        rs.getString("nome"),
                        rs.getString("cnpj"));
            } else {
                throw new RuntimeException("Id do fornecedor não encontrado!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Fornecedor> buscarTodos() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();

        String sql = """
                SELECT id, nome, cnpj
                FROM Fornecedor;""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                fornecedores.add(new Fornecedor(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cnpj")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fornecedores;
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {
        if (fornecedor.getId() != buscarPorId(fornecedor.getId()).getId()) {
            throw new RuntimeException("Id do fornecedor não encontrado!");
        }

        String sql = """
                UPDATE Fornecedor
                SET nome = ?, cnpj = ?
                WHERE id = ?;
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setInt(3, fornecedor.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletarFornecedor(int id) throws SQLException {
        if (id != buscarPorId(id).getId()) {
            throw new RuntimeException("Id do fornecedor não encontrado!");
        }

        String sql = """
                DELETE FROM Fornecedor
                WHERE id = ?""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

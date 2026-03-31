package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public void salvar(Contato contato) throws SQLException {
           String command = """
                   INSERT INTO contato
                   (nome, numero)
                   VALUES
                   (?,?);
                 """;

           try (Connection conn = Conexao.conectar();
               PreparedStatement stmt = conn.prepareStatement(command)) {
               stmt.setString(1, contato.getNome());
               stmt.setString(2, contato.getNumero());
               stmt.executeUpdate();
           }
    }

    public List<Contato> verContato() throws SQLException {
        List<Contato> contato = new ArrayList<>();
        String command = """
                   SELECT id,
                          nome,
                          numero 
                   FROM contato;""";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero = rs.getString("numero");

                contato.add(new Contato(id, nome, numero));
            }
            return contato;
        }
    }

    public List<Contato> verContato(String pedacoNome) throws SQLException{
        List<Contato> contatos = new ArrayList<>();
        String command = """
                SELECT id,
                       nome,
                       numero
                FROM contato
                WHERE nome LIKE ?""";
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, "'%" + pedacoNome + "%'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero = rs.getString("numero");

                contatos.add(new Contato(id, nome, numero));
            }
        }
        return contatos;
    }

    public List<Contato> verVariosContatos(ArrayList<Integer> ids) throws SQLException {
        List<Contato> contatos = new ArrayList<>();
        String sequenciaIds = "";
        for (int i = 0; i < ids.size(); i++) {
            if (ids.size() - 1 != i) {
                sequenciaIds += ids.get(i) + ",";
            } else {
                sequenciaIds += ids.get(i);
            }
        }
        String command = """
                SELECT id,
                       nome,
                       numero
                FROM contato
                WHERE id IN (%s)""".formatted(sequenciaIds);
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numero = rs.getString("numero");

                contatos.add(new Contato(id, nome, numero));
            }
        }
        return contatos;
    }

    public Contato selecionarContato(int id) throws SQLException {
        String command = """
                   SELECT id,
                          nome
                   FROM contato;""";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    return new Contato(rs.getInt("id"), rs.getString("nome"), rs.getString("numero"));
                }
            }
        }
        return new Contato(0, "Null", "Null");
    }

    public void atualizarNomeContato(String nome, Contato contato) throws SQLException {
        String command = """
                UPDATE contato
                SET nome = ?,
                WHERE id = ?;""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, nome);
            stmt.setInt(2, contato.getId());
            stmt.executeUpdate();
        }
    }

    public void atualizarNumeroContato(String numero, Contato contato) throws SQLException {
        String command = """
                UPDATE contato
                SET numero = ?,
                WHERE id = ?;""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, numero);
            stmt.setInt(2, contato.getId());
            stmt.executeUpdate();
        }
    }

    public void atualizarContatoCompleto(String nome, String numero, Contato contato) throws SQLException {
        String command = """
                UPDATE contato
                SET nome = ?, numero = ?
                WHERE id = ?;""";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, numero);
            stmt.setString(2, nome);
            stmt.setInt(3, contato.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirContato(Contato contato) throws SQLException {
        String command = """
                DElETE FROM contato
                WHERE id = ?;""";
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setInt(1, contato.getId());
            stmt.executeUpdate();
        }
    }

    public static ArrayList<Grupo> verGrupos(Contato contato) throws SQLException {
        ArrayList<Grupo> grupos = new ArrayList<Grupo>();
        String command = """
                SELECT id, 
                       contatoID, 
                       grupoID
                FROM contato_grupo
                WHERE contatoID = ?""";
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmtBuscarGrupos = conn.prepareStatement(command)) {
            stmtBuscarGrupos.setInt(1, contato.getId());
            ResultSet rsContatoGrupo = stmtBuscarGrupos.executeQuery();
            while (rsContatoGrupo.next()) {
                command = """
                    SELECT id,
                           nome
                    FROM grupo
                    WHERE id = ?""";
                PreparedStatement stmtRetornarGrupos = conn.prepareStatement(command);
                stmtRetornarGrupos.setInt(1, rsContatoGrupo.getInt("id"));
                ResultSet rsGrupo = stmtRetornarGrupos.executeQuery();
                grupos.add(new Grupo(rsGrupo.getInt("id"), rsGrupo.getString("nome")));
            }
        }
        return grupos;
    }
}
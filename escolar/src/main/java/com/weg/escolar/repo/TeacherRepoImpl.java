package com.weg.escolar.repo;

import com.weg.escolar.infra.ConnectionFactory;
import com.weg.escolar.model.Teacher;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepoImpl implements TeacherRepo {
    @Override
    public Teacher createTeacher(Teacher teacher) throws SQLException {
        String sql = """
                INSERT INTO professor(
                    nome, email, disciplina)
                VALUES (?,?,?);""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, teacher.getNome());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getDisciplina());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                teacher.setId(rs.getLong(1));
            } else {
                throw new RuntimeException("Estudante não cadastrado");
            }
        }
        return teacher;
    }

    @Override
    public Optional<Teacher> getTeacher(Long id) throws SQLException {
        String sql = """
                SELECT nome, email, disciplina
                FROM professor
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Teacher(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("disciplina")
                ));
            } else {
                throw new RuntimeException("Professor não cadastrado");
            }
        }
    }

    @Override
    public List<Teacher> getAllTeachers() throws SQLException {
        List<Teacher> students = new ArrayList<>();

        String sql = """
                SELECT nome, email, disciplina
                FROM professor;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(new Teacher(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("disciplina")
                ));
            }
        }
        return students;
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws SQLException {
        String sql = """
                UPDATE aluno
                SET
                    nome = ?,
                    email = ?,
                    disciplina = ?
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getNome());
            stmt.setString(2, teacher.getEmail());
            stmt.setString(3, teacher.getDisciplina());

            stmt.setLong(4, teacher.getId());

            stmt.executeUpdate();

            return teacher;
        }
    }

    @Override
    public void deleteTeacher(Long id) throws SQLException {
        String sql = """
                DELETE FROM professor
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}

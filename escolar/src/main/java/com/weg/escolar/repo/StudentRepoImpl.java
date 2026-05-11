package com.weg.escolar.repo;

import com.weg.escolar.infra.ConnectionFactory;
import com.weg.escolar.model.Student;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepoImpl implements StudentRepo {
    @Override
    public Student createStudent(Student student) throws SQLException {
        String sql = """
                INSERT INTO aluno(
                    nome, email, matricula, data_nascimento)
                VALUES (?,?,?,?);""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getNome());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getMatricula());
            stmt.setDate(4, Date.valueOf(student.getDataNascimento()));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                student.setId(rs.getLong(1));
            } else {
                throw new RuntimeException("Estudante não cadastrado");
            }
        }
        return student;
    }

    @Override
    public Optional<Student> getStudent(Long id) throws SQLException {
        String sql = """
                SELECT nome, email, matricula, data_nascimento
                FROM aluno
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Student(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getDate("data_nascimento").toLocalDate()
                ));
            } else {
                throw new RuntimeException("Estudante não cadastrado");
            }
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();

        String sql = """
                SELECT nome, email, matricula, data_nascimento
                FROM aluno;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                students.add(new Student(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getDate("data_nascimento").toLocalDate())
                );
            }
        }
        return students;
    }

    @Override
    public Student updateStudent(Student student) throws SQLException {
        String sql = """
                UPDATE aluno
                SET
                    nome = ?,
                    email = ?,
                    matricula = ?,
                    data_nascimento = ?
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getNome());
            stmt.setString(2, student.getEmail());
            stmt.setString(3, student.getMatricula());
            stmt.setDate(4, Date.valueOf(student.getDataNascimento()));

            stmt.setLong(5, student.getId());

            stmt.executeUpdate();

            return student;
        }
    }

    @Override
    public void deleteStudent(Long id) throws SQLException {
        String sql = """
                DELETE FROM aluno
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }
}

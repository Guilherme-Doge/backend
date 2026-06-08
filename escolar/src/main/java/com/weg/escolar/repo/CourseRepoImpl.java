package com.weg.escolar.repo;

import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.infra.ConnectionFactory;
import com.weg.escolar.model.Course;
import com.weg.escolar.model.Class;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class CourseRepoImpl implements CourseRepo {

    @Override
    public Course createCourse(Course course) throws SQLException {
        String sql = """
                INSERT INTO curso(
                    nome, codigo)
                VALUES (?,?);""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, course.getNome());
            stmt.setString(2, course.getCodigo());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                course.setId(rs.getLong(1));
                return course;
            } else {
                throw new RuntimeException("Curso não cadastrado");
            }
        }
    }

    @Override
    public Optional<Course> getCourse(Long id) throws SQLException {
        String sql = """
             SELECT c.id,
                    c.nome,
                    c.codigo,
                    t.id AS turma_id,
                    p.id AS professor_id
             FROM curso AS c
             LEFT JOIN turma AS t
                 ON t.curso_id = c.id
             LEFT JOIN professor AS p
                 ON p.id = t.professor_id
             WHERE c.id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            String name = null;
            String code = null;
            List<Long> teachersIds = new ArrayList<>();

            while (rs.next()) {
                if (name == null) {
                    name = rs.getString("nome");
                    code = rs.getString("codigo");
                }

                long profId = rs.getLong("professor_id");
                if (!rs.wasNull()) {
                    teachersIds.add(profId);
                }
            }

            if (name != null) {
                return Optional.of(new Course(name, code, teachersIds));
            }

            return Optional.empty();
        }
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        List<Long> teachersIds = new ArrayList<>();

        String sql = """
                SELECT c.id AS curso_id,
                    c.nome,
                    c.codigo,
                    t.id AS turma_id,
                    p.id AS professor_id
                FROM curso AS c
                INNER JOIN turma AS t
                    ON t.curso_id = c.id
                INNER JOIN professor AS p
                    ON p.id = professor_id;""";

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            Map<Long, Course> courseMap = new LinkedHashMap<>();

            while (rs.next()) {
                Long cursoId = rs.getLong("curso_id");

                Course course = courseMap.computeIfAbsent(cursoId, id -> {
                    try {
                        return new Course(
                                rs.getString("nome"),
                                rs.getString("codigo"),
                                new ArrayList<>()
                        );
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                course.getListaProfessorIds().add(rs.getLong("professor_id"));
            }

            return new ArrayList<>(courseMap.values());
        }
    }

    @Override
    public Course updateCourse(Course course) throws SQLException {
        String sql = """
                UPDATE curso
                SET nome = ?,
                    codigo = ?
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getNome());
            stmt.setString(2, course.getCodigo());

            stmt.setLong(3, course.getId());

            stmt.executeUpdate();

            return course;
        }
    }

    @Override
    public void deleteCourse(Long id) throws SQLException {
        String sql = """
                DELETE FROM curso
                WHERE id = ?;""";

        try (Connection conn = ConnectionFactory.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Class> getCourseClasses(Long id) throws SQLException {
        List<Class> classes = new ArrayList<>();

        String sql = """
            SELECT
                t.id,
                t.nome,
                t.curso_id,
                t.professor_id,
                a.id AS aluno_id,
                a.nome AS aluno_nome
            FROM turma t
            INNER JOIN turma_aluno ta
                ON t.id = ta.turma_id
            INNER JOIN aluno a
                ON ta.aluno_id = a.id
            WHERE t.curso_id = ?;
            """;

        try (Connection conn = ConnectionFactory.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            Map<Long, Class> turmaMap = new HashMap<>();

            while (rs.next()) {

                Long turmaId = rs.getLong("id");
                Class turma = turmaMap.get(turmaId);

                if (turma == null) {
                    turma = new Class(
                            rs.getString("nome"),
                            rs.getLong("curso_id"),
                            rs.getLong("professor_id"),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );

                    turma.setListaAlunoNomes(new ArrayList<>());

                    turmaMap.put(turmaId, turma);
                }

                turma.getListaAlunoNomes()
                        .add(rs.getString("aluno_nome"));
            }
            classes.addAll(turmaMap.values());
        }
        return classes;
    }
}

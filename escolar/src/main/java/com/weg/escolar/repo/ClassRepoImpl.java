package com.weg.escolar.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClassRepoImpl implements ClassRepo {
    @Override
    public Class createClass(Class classEntity) throws SQLException {
        String sql = """
                INSERT INTO turma
                    (nome, 
                    curso_id, 
                    professor_id)
                VALUES (?,?,?)""";
    }

    @Override
    public Optional<Class> getClass(Long id) throws SQLException {

    }

    @Override
    public List<Class> getAllClass() throws SQLException {

    }

    @Override
    public Class updateClass(Class classEntity) throws SQLException {
        ;
    }

    @Override
    public void deleteClass(Long id) throws SQLException {

    }

    @Override
    public List<Class> getClassStudents(Long id) throws SQLException {

    }
}

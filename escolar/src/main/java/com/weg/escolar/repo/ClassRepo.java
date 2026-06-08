package com.weg.escolar.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClassRepo {
    Class createClass(Class classEntity) throws SQLException;

    Optional<Class> getClass(Long id) throws SQLException;

    List<Class> getAllClass() throws SQLException;

    Class updateClass(Class classEntity) throws SQLException;

    void deleteClass(Long id) throws SQLException;

    List<Class> getClassStudents(Long id) throws SQLException;
}

package com.weg.escolar.repo;

import com.weg.escolar.model.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TeacherRepo {
    Teacher createTeacher(Teacher teacher) throws SQLException;

    Optional<Teacher> getTeacher(Long id) throws SQLException;

    List<Teacher> getAllTeachers() throws SQLException;

    Teacher updateTeacher(Teacher teacher) throws SQLException;

    void deleteTeacher(Long id) throws SQLException;
}

package com.weg.escolar.repo;

import com.weg.escolar.model.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface StudentRepo {
    Student createStudent(Student student) throws SQLException;

    Optional<Student> getStudent(Long id) throws SQLException;

    List<Student> getAllStudents() throws SQLException;

    Student updateStudent(Student student) throws SQLException;

    void deleteStudent(Long id) throws SQLException;
}

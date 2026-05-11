package com.weg.escolar.service;

import com.weg.escolar.dto.StudentRequestDto;
import com.weg.escolar.dto.StudentResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto) throws SQLException;

    StudentResponseDto getStudent(Long id) throws SQLException;

    List<StudentResponseDto> getAllStudents() throws SQLException;

    StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, Long id) throws SQLException;

    void deleteStudent(Long id) throws SQLException;
}

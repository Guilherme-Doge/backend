package com.weg.escolar.service;

import com.weg.escolar.dto.ClassRequestDto;
import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.dto.StudentResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface ClassService {
    ClassResponseDto createClass(ClassRequestDto classRequestDto) throws SQLException;

    ClassResponseDto getClass(Long id) throws SQLException;

    List<ClassResponseDto> getAllClasses() throws SQLException;

    ClassResponseDto updateClass(ClassRequestDto classRequestDto, Long id) throws SQLException;

    void deleteClass(Long id) throws SQLException;

    List<StudentResponseDto> getClassStudents(Long id) throws SQLException;
}

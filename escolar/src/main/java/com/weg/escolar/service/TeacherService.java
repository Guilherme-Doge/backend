package com.weg.escolar.service;

import com.weg.escolar.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface TeacherService {
    TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) throws SQLException;

    TeacherResponseDto getTeacher(Long id) throws SQLException;

    List<TeacherResponseDto> getAllTeachers() throws SQLException;

    TeacherResponseDto updateTeacher(TeacherRequestDto teacherRequestDto, Long id) throws SQLException;

    void deleteTeacher(Long id) throws SQLException;
}

package com.weg.escolar.service;

import com.weg.escolar.dto.ClassRequestDto;
import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.dto.StudentResponseDto;

import java.sql.SQLException;
import java.util.List;

public class ClassServiceImpl implements ClassService {
    @Override
    public ClassResponseDto createClass(ClassRequestDto classRequestDto) throws SQLException {
        return null;
    }

    @Override
    public ClassResponseDto getClass(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<ClassResponseDto> getAllClasses() throws SQLException {
        return List.of();
    }

    @Override
    public ClassResponseDto updateClass(ClassRequestDto classRequestDto, Long id) throws SQLException {
        return null;
    }

    @Override
    public void deleteClass(Long id) throws SQLException {

    }

    @Override
    public List<StudentResponseDto> getClassStudents(Long id) throws SQLException {
        return List.of();
    }
}

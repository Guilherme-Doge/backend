package com.weg.escolar.service;

import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.dto.CourseRequestDto;
import com.weg.escolar.dto.CourseResponseDto;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto courseRequestDto) throws SQLException;

    CourseResponseDto getCourse(Long id) throws SQLException;

    List<CourseResponseDto> getAllCourses() throws SQLException;

    CourseResponseDto updateCourse(CourseRequestDto courseRequestDto, Long id) throws SQLException;

    void deleteCourse(Long id) throws SQLException;

    List<ClassResponseDto> getCourseClasses(Long id) throws SQLException;
}

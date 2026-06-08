package com.weg.escolar.mapper;

import com.weg.escolar.dto.CourseRequestDto;
import com.weg.escolar.dto.CourseResponseDto;
import com.weg.escolar.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toEntity(CourseRequestDto courseRequestDto) {
        return new Course(courseRequestDto.nome(),
                courseRequestDto.codigo());
    }

    public CourseResponseDto toResponse(Course course) {
        return new CourseResponseDto(course.getNome(),
                course.getCodigo(),
                course.getListaProfessorIds());
    }
}

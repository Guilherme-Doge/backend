package com.weg.escolar.service;

import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.dto.CourseRequestDto;
import com.weg.escolar.dto.CourseResponseDto;
import com.weg.escolar.mapper.CourseMapper;
import com.weg.escolar.model.Course;
import com.weg.escolar.model.Class;
import com.weg.escolar.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo repo;
    private final CourseMapper mapper;

    public CourseServiceImpl(CourseRepo repo, CourseMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) throws SQLException {
        Course course = mapper.toEntity(courseRequestDto);

        return mapper.toResponse(repo.createCourse(course));
    }

    @Override
    public CourseResponseDto getCourse(Long id) throws SQLException {
        return mapper.toResponse(repo.getCourse(id).orElseThrow(
                () -> new RuntimeException("Curso não encontrado")
        ));
    }

    public List<CourseResponseDto> getAllCourses() throws SQLException {
        return repo.getAllCourses().stream()
                .map(course -> mapper.toResponse(course))
                .toList();
    }

    @Override
    public CourseResponseDto updateCourse(CourseRequestDto courseRequestDto, Long id) throws SQLException {
        Course course = mapper.toEntity(courseRequestDto);

        course.setId(id);

        repo.updateCourse(course);

        return mapper.toResponse(repo.getCourse(id).orElseThrow(
                () -> new RuntimeException("Curso não encontrado")
        ));
    }

    @Override
    public void deleteCourse(Long id) throws SQLException {
        repo.deleteCourse(id);
    }

    @Override
    public List<ClassResponseDto> getCourseClasses(Long id) throws SQLException {

        return repo.getCourseClasses(id)
                .stream()
                .map(turma -> new ClassResponseDto(
                        turma.getCursoId(),
                        turma.getProfessorId(),
                        turma.getListaAlunoNomes()
                ))
                .toList();
    }
}

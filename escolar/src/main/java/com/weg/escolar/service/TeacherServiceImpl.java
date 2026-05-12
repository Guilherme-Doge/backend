package com.weg.escolar.service;

import com.weg.escolar.dto.TeacherResponseDto;
import com.weg.escolar.dto.TeacherRequestDto;
import com.weg.escolar.mapper.TeacherMapper;
import com.weg.escolar.model.Teacher;
import com.weg.escolar.repo.TeacherRepo;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper mapper;
    private final TeacherRepo repo;

    public TeacherServiceImpl(TeacherMapper mapper, TeacherRepo repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public TeacherResponseDto createTeacher(TeacherRequestDto teacherRequestDto) throws SQLException {
        Teacher teacher = mapper.toEntity(teacherRequestDto);

        return mapper.toResponse(repo.createTeacher(teacher));
    }

    @Override
    public TeacherResponseDto getTeacher(Long id) throws SQLException {
        return mapper.toResponse(repo.getTeacher(id)
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado")));
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() throws SQLException {
        return repo.getAllTeachers().stream()
                .map(teacher -> new TeacherResponseDto(teacher.getNome(),
                        teacher.getEmail(),
                        teacher.getDisciplina()))
                .toList();
    }

    @Override
    public TeacherResponseDto updateTeacher(TeacherRequestDto teacherRequestDto, Long id) throws SQLException {
        Teacher teacher = mapper.toEntity(teacherRequestDto);
        teacher.setId(id);

        repo.updateTeacher(teacher);

        return mapper.toResponse(repo.getTeacher(teacher.getId())
                .orElseThrow(() -> new RuntimeException("Estudante não encontrado")));
    }

    @Override
    public void deleteTeacher(Long id) throws SQLException {
        repo.deleteTeacher(id);
    }
}

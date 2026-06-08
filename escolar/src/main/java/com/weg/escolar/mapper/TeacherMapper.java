package com.weg.escolar.mapper;

import com.weg.escolar.dto.TeacherRequestDto;
import com.weg.escolar.dto.TeacherResponseDto;
import com.weg.escolar.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    public TeacherResponseDto toResponse(Teacher teacher) {
        return new TeacherResponseDto(
                teacher.getId(),
                teacher.getNome(),
                teacher.getEmail(),
                teacher.getDisciplina()
        );
    }

    public Teacher toEntity(TeacherRequestDto teacherRequestDto) {
        return new Teacher(
                teacherRequestDto.nome(),
                teacherRequestDto.email(),
                teacherRequestDto.disciplina()
        );
    }
}

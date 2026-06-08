package com.weg.escolar.mapper;

import com.weg.escolar.dto.StudentRequestDto;
import com.weg.escolar.dto.StudentResponseDto;
import com.weg.escolar.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentResponseDto toResponse(Student student) {
        return new StudentResponseDto(
                student.getDataNascimento()
        );
    }

    public Student toEntity(StudentRequestDto studentRequestDto) {
        return new Student(
                studentRequestDto.nome(),
                studentRequestDto.email(),
                studentRequestDto.matricula(),
                studentRequestDto.dataNascimento()
        );
    }
}

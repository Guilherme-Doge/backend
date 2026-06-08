package com.weg.escolar.mapper;

import com.weg.escolar.dto.ClassRequestDto;
import com.weg.escolar.dto.ClassResponseDto;
import com.weg.escolar.model.Class;

import java.util.List;

public class ClassMapper {
    public Class toEntity(ClassRequestDto classRequestDto) {
        return new Class(classRequestDto.nome(),
                classRequestDto.cursoId(),
                classRequestDto.professorId(),
                classRequestDto.listaAlunoIds());
    }

    public ClassResponseDto toResponse(Class classEntity, List<String> listaAlunosIds) {
        return new ClassResponseDto(classEntity.getCursoId(),
                classEntity.getProfessorId(),
                classEntity.getListaAlunoNomes());
    }
}
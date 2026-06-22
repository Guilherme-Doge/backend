package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.ProfessorCursoResumoDto;
import com.weg.jpa_relacionamentos.dto.ProfessorRequestDto;
import com.weg.jpa_relacionamentos.dto.ProfessorResponseDto;
import com.weg.jpa_relacionamentos.model.Professor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfessorMapper {
    public Professor toEntity(ProfessorRequestDto dto) {
        return new Professor(dto.nome());
    }

    public ProfessorResponseDto toResponse(Professor professor) {
        List<ProfessorCursoResumoDto> cursos = professor.getCursos() == null ? List.of() : professor.getCursos().stream()
                .map(curso -> new ProfessorCursoResumoDto(curso.getId(), curso.getTitulo()))
                .toList();
        return new ProfessorResponseDto(professor.getId(), professor.getNome(), cursos);
    }
}

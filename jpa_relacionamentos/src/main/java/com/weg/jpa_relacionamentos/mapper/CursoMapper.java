package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.CursoRequestDto;
import com.weg.jpa_relacionamentos.dto.CursoResponseDto;
import com.weg.jpa_relacionamentos.model.Curso;
import com.weg.jpa_relacionamentos.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public Curso toEntity(CursoRequestDto dto, Professor professor) {
        Curso curso = new Curso();
        curso.setTitulo(dto.titulo());
        curso.setProfessor(professor);
        return curso;
    }

    public CursoResponseDto toResponse(Curso curso) {
        return new CursoResponseDto(
                curso.getId(),
                curso.getTitulo(),
                curso.getProfessor() != null ? curso.getProfessor().getId() : null,
                curso.getProfessor() != null ? curso.getProfessor().getNome() : null
        );
    }
}

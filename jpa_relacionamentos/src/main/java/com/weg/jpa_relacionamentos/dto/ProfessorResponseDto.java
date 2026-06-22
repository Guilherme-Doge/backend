package com.weg.jpa_relacionamentos.dto;

import java.util.List;

public record ProfessorResponseDto(
        Long id,
        String nome,
        List<ProfessorCursoResumoDto> cursos
) {}

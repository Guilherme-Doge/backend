package com.weg.jpa_relacionamentos.dto;

public record CursoResponseDto(
        Long id,
        String titulo,
        Long professorId,
        String professorNome
) {}

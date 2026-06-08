package com.weg.escolar.dto;

public record TeacherResponseDto(
        Long id,
        String nome,
        String email,
        String disciplina) {
}

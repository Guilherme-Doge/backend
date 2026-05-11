package com.weg.escolar.dto;

import java.time.LocalDate;

public record StudentRequestDto(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}
package com.weg.escolar.dto;

import java.time.LocalDate;

public record StudentResponseDto(
        String nome,
        String email,
        String matricula,
        LocalDate dataNascimento
) {
}

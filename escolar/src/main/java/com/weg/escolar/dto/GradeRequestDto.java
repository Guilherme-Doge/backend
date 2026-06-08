package com.weg.escolar.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record GradeRequestDto(
        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long alunoId,

        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long aulaId,

        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long valorId
) {
}

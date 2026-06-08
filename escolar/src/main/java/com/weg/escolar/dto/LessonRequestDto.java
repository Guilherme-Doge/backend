package com.weg.escolar.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record LessonRequestDto(
        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long turmaId,

        @NotNull(message = "Data/hora não pode ser nulo")
        LocalDateTime dataHora,

        @NotBlank(message = "Assunto não pode estar em branco")
        String assunto,

        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long id,

        @NotBlank(message = "Nome não pode estar em branco")
        String nome,

        @Email(message = "Formato inválido! Formato válido: email@dominio")
        String email,

        @NotBlank(message = "Matrícula não pode estar em branco")
        @Pattern(regexp = "^\\d{7,}$", message = "Formato inválido! Formato válido: 2026001")
        String matricula
) {
}

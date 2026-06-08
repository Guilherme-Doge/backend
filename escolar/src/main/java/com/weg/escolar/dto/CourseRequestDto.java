package com.weg.escolar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CourseRequestDto(
        @NotBlank(message = "Nome não pode estar em branco")
        String nome,

        @NotBlank(message = "Código não pode estar em branco")
        @Pattern(regexp = "^[a-zA-Z]{2,}\\d{2}$", message = "Formato inválido! Formato válido: DG02")
        String codigo) {
}

package com.weg.escolar.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentRequestDto(
        @NotBlank(message = "Nome não pode estar em branco")
        String nome,

        @NotBlank(message = "Nome não pode estar em branco")
        @Email(message = "Formato inválido! Formato válido: contato@dominio")
        String email,

        @NotBlank(message = "Matricula não pode estar em branco")
        @Pattern(regexp = "^\\d{7,}$", message = "Formato inválido! Formato válido: 2025001")
        String matricula,

        @NotNull(message = "Data de nascimento não pode estar vazia")
        @Past(message = "Data de nascimento deve ser no passado")
        LocalDate dataNascimento
) {
}
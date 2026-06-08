package com.weg.escolar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TeacherRequestDto(
        @NotBlank(message = "Nome não pode estar em branco")
        String nome,

        @NotBlank(message = "Email não pode estar em branco")
        @Email(message = "Formato inválido! Formato válido: contato@dominio")
        String email,

        @NotBlank(message = "Disciplina não pode estar em branco")
        String disciplina) {
}

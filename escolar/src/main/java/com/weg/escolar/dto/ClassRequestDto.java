package com.weg.escolar.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record ClassRequestDto(

        @NotBlank(message = "Nome não pode estar em branco")
        @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
        String nome,

        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long cursoId,

        @Min(value = 1, message = "ID 0 não existe no Banco de Dados")
        @Positive(message = "ID deve ser positivo")
        Long professorId,

        @NotEmpty(message = "Lista de IDs de alunos deve ter pelo menos 1 valor")
        List<Long> listaAlunoIds) {
}

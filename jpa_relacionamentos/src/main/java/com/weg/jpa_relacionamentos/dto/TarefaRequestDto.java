package com.weg.jpa_relacionamentos.dto;

public record TarefaRequestDto(
        String titulo,
        String descricao,
        Long projetoId
) {}

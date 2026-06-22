package com.weg.jpa_relacionamentos.dto;

public record TarefaResponseDto(
        Long id,
        String titulo,
        String descricao,
        Long projetoId,
        String projetoNome
) {}

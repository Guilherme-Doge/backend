package com.weg.jpa_relacionamentos.dto;

public record PedidoResponseDto(
        Long id,
        String descricao,
        Long clienteId,
        String clienteNome
) {}

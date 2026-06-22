package com.weg.jpa_relacionamentos.dto;

public record PedidoRequestDto(
        String descricao,
        Long clienteId
) {}

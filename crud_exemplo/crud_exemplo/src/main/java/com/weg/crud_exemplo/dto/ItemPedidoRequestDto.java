package com.weg.crud_exemplo.dto;

public record ItemPedidoRequestDto(
        String nome,
        Double preco,
        Long pedidoId
) {
}

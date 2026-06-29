package com.weg.crud_exemplo.dto;

import com.weg.crud_exemplo.model.Pedido;

public record ItemPedidoResponseDto(
        Long id,
        String nome,
        Double preco,
        Pedido pedido
) {
}

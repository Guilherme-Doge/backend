package com.weg.crud_exemplo.mapper;

import com.weg.crud_exemplo.dto.ItemPedidoRequestDto;
import com.weg.crud_exemplo.dto.ItemPedidoResponseDto;
import com.weg.crud_exemplo.dto.PedidoResponseDto;
import com.weg.crud_exemplo.model.ItemPedido;
import com.weg.crud_exemplo.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {
    public ItemPedido toEntity(ItemPedidoRequestDto itemPedidoRequestDto) {
        return new ItemPedido(itemPedidoRequestDto.nome(),
                            itemPedidoRequestDto.preco(),
                            new Pedido(itemPedidoRequestDto.pedidoId()));
    }

    public ItemPedidoResponseDto toResponse(ItemPedido itemPedido) {
        return new ItemPedidoResponseDto(itemPedido.getId(),
                                        itemPedido.getNome(),
                                        itemPedido.getPreco(),
                                        itemPedido.getPedido());
    }
}

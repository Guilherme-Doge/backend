package com.weg.crud_exemplo.mapper;

import com.weg.crud_exemplo.dto.PedidoRequestDto;
import com.weg.crud_exemplo.dto.PedidoResponseDto;
import com.weg.crud_exemplo.model.Pedido;
import org.springframework.stereotype.Component;


@Component
public class PedidoMapper {
    public Pedido toEntity(PedidoRequestDto pedidoRequestDto) {
        return new Pedido(pedidoRequestDto.dataCriacao(),
                        pedidoRequestDto.nomeCliente());
    }

    public PedidoResponseDto toResponse(Pedido pedido) {
        return new PedidoResponseDto(pedido.getId(),
                                    pedido.getDataCriacao(),
                                    pedido.getNomeCliente());
    }
}

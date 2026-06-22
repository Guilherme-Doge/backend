package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.PedidoRequestDto;
import com.weg.jpa_relacionamentos.dto.PedidoResponseDto;
import com.weg.jpa_relacionamentos.model.Cliente;
import com.weg.jpa_relacionamentos.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public Pedido toEntity(PedidoRequestDto dto, Cliente cliente) {
        return new Pedido(dto.descricao(), cliente);
    }

    public PedidoResponseDto toResponse(Pedido pedido) {
        return new PedidoResponseDto(
                pedido.getId(),
                pedido.getDescricao(),
                pedido.getCliente() != null ? pedido.getCliente().getId() : null,
                pedido.getCliente() != null ? pedido.getCliente().getNome() : null
        );
    }
}

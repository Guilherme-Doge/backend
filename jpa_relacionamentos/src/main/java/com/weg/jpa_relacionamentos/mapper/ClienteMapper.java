package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.ClienteRequestDto;
import com.weg.jpa_relacionamentos.dto.ClienteResponseDto;
import com.weg.jpa_relacionamentos.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public Cliente toEntity(ClienteRequestDto dto) {
        return new Cliente(dto.nome());
    }

    public ClienteResponseDto toResponse(Cliente cliente) {
        return new ClienteResponseDto(cliente.getId(), cliente.getNome());
    }
}

package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.ClienteRequestDto;
import com.weg.jpa_relacionamentos.dto.ClienteResponseDto;
import com.weg.jpa_relacionamentos.mapper.ClienteMapper;
import com.weg.jpa_relacionamentos.model.Cliente;
import com.weg.jpa_relacionamentos.repo.ClienteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepo clienteRepo;

    public ClienteResponseDto post(ClienteRequestDto dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("Informe o nome do cliente");
        }
        return clienteMapper.toResponse(clienteRepo.save(clienteMapper.toEntity(dto)));
    }

    public ClienteResponseDto get(Long id) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não existe"));
        return clienteMapper.toResponse(cliente);
    }

    public List<ClienteResponseDto> list() {
        return clienteRepo.findAll().stream().map(clienteMapper::toResponse).toList();
    }

    public ClienteResponseDto put(Long id, ClienteRequestDto dto) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não existe"));
        if (dto.nome() != null && !dto.nome().isBlank()) {
            cliente.setNome(dto.nome());
        }
        return clienteMapper.toResponse(clienteRepo.save(cliente));
    }

    public void delete(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new RuntimeException("Cliente não existe");
        }
        clienteRepo.deleteById(id);
    }
}

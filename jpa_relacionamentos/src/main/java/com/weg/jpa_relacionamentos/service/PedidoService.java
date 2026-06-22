package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.PedidoRequestDto;
import com.weg.jpa_relacionamentos.dto.PedidoResponseDto;
import com.weg.jpa_relacionamentos.mapper.PedidoMapper;
import com.weg.jpa_relacionamentos.model.Cliente;
import com.weg.jpa_relacionamentos.model.Pedido;
import com.weg.jpa_relacionamentos.repo.ClienteRepo;
import com.weg.jpa_relacionamentos.repo.PedidoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoMapper pedidoMapper;
    private final PedidoRepo pedidoRepo;
    private final ClienteRepo clienteRepo;

    public PedidoResponseDto post(PedidoRequestDto dto) {
        if (dto.clienteId() == null) {
            throw new RuntimeException("Informe o id do cliente");
        }
        Cliente cliente = clienteRepo.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não existe"));
        Pedido pedido = pedidoMapper.toEntity(dto, cliente);
        return pedidoMapper.toResponse(pedidoRepo.save(pedido));
    }

    public PedidoResponseDto get(Long id) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não existe"));
        return pedidoMapper.toResponse(pedido);
    }

    public List<PedidoResponseDto> list() {
        return pedidoRepo.findAll().stream().map(pedidoMapper::toResponse).toList();
    }

    public PedidoResponseDto put(Long id, PedidoRequestDto dto) {
        Pedido pedido = pedidoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não existe"));
        if (dto.descricao() != null && !dto.descricao().isBlank()) {
            pedido.setDescricao(dto.descricao());
        }
        if (dto.clienteId() != null) {
            Cliente cliente = clienteRepo.findById(dto.clienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não existe"));
            pedido.setCliente(cliente);
        }
        return pedidoMapper.toResponse(pedidoRepo.save(pedido));
    }

    public void delete(Long id) {
        if (!pedidoRepo.existsById(id)) {
            throw new RuntimeException("Pedido não existe");
        }
        pedidoRepo.deleteById(id);
    }
}

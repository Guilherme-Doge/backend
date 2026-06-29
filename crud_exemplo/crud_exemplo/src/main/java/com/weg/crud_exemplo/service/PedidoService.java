package com.weg.crud_exemplo.service;

import com.weg.crud_exemplo.dto.ItemPedidoResponseDto;
import com.weg.crud_exemplo.dto.PedidoRequestDto;
import com.weg.crud_exemplo.dto.PedidoResponseDto;
import com.weg.crud_exemplo.mapper.PedidoMapper;
import com.weg.crud_exemplo.model.ItemPedido;
import com.weg.crud_exemplo.model.Pedido;
import com.weg.crud_exemplo.repo.ItemPedidoRepo;
import com.weg.crud_exemplo.repo.PedidoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoMapper pedidoMapper;

    private final PedidoRepo pedidoRepo;

    private final ItemPedidoRepo itemPedidoRepo;

    public PedidoResponseDto post(PedidoRequestDto pedidoRequestDto) throws Exception {
        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDto);

        pedidoRepo.save(pedido);

        pedido = pedidoRepo.findById(pedido.getId()).orElseThrow(() -> new RuntimeException("Pedido não cadastrado"));

        return pedidoMapper.toResponse(pedido);
    }

    public PedidoResponseDto get(Long id) throws Exception {
        Pedido pedido = pedidoRepo.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        return pedidoMapper.toResponse(pedido);
    }

    public List<PedidoResponseDto> getAll() throws Exception {
        List<Pedido> pedidos = pedidoRepo.findAll();

        return pedidos.stream().map(pedidoMapper::toResponse).toList();
    }


    public PedidoResponseDto put(PedidoRequestDto pedidoRequestDto, Long id) throws Exception {
        Pedido pedido = pedidoRepo.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (pedidoRequestDto.dataCriacao() != null) {
            pedido.setDataCriacao(pedidoRequestDto.dataCriacao());
        }

        if (pedidoRequestDto.nomeCliente() != null) {
            pedido.setNomeCliente(pedidoRequestDto.nomeCliente());
        }

        if (pedidoRequestDto.itensId() != null) {
            pedido.setItens(itemPedidoRepo.findBypedido_id(pedido.getId()));
        }

        return pedidoMapper.toResponse(pedido);
    }

    public void delete(Long id) throws Exception {
        pedidoRepo.deleteById(id);
    }

}

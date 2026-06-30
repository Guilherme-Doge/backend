package com.weg.crud_exemplo.service;

import com.weg.crud_exemplo.dto.ItemPedidoRequestDto;
import com.weg.crud_exemplo.dto.ItemPedidoResponseDto;
import com.weg.crud_exemplo.dto.PedidoResponseDto;
import com.weg.crud_exemplo.dto.RelatorioSimplesResponseDto;
import com.weg.crud_exemplo.mapper.ItemPedidoMapper;
import com.weg.crud_exemplo.mapper.PedidoMapper;
import com.weg.crud_exemplo.model.ItemPedido;
import com.weg.crud_exemplo.model.Pedido;
import com.weg.crud_exemplo.projection.RelatorioSimplesProjection;
import com.weg.crud_exemplo.repo.ItemPedidoRepo;
import com.weg.crud_exemplo.repo.PedidoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoMapper itemPedidoMapper;

    private final ItemPedidoRepo itemPedidoRepo;

    private final PedidoRepo pedidoRepo;

    private final PedidoMapper pedidoMapper;

    public ItemPedidoResponseDto post(ItemPedidoRequestDto itemPedidoRequestDto) throws Exception {
        ItemPedido itemPedido = itemPedidoMapper.toEntity(itemPedidoRequestDto);

        itemPedidoRepo.save(itemPedido);

        itemPedido = itemPedidoRepo.findById(itemPedido.getId()).orElseThrow(() -> new RuntimeException("ItemPedido não cadastrado"));

        PedidoResponseDto pedidoResponseDto = pedidoMapper.toResponse(pedidoRepo.findById(itemPedido.getPedido().getId()).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));

        return itemPedidoMapper.toResponse(itemPedido);
    }

    public ItemPedidoResponseDto get(Long id) throws Exception {
        ItemPedido itemPedido = itemPedidoRepo.findById(id).orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));

        PedidoResponseDto pedidoResponseDto = pedidoMapper.toResponse(pedidoRepo.findById(itemPedido.getPedido().getId()).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));

        return itemPedidoMapper.toResponse(itemPedido);
    }

    public List<ItemPedidoResponseDto> getAll() throws Exception {
        List<ItemPedido> itemPedidos = itemPedidoRepo.findAll();

        return itemPedidos.stream().map(itemPedidoMapper::toResponse).toList();
    }

    public List<RelatorioSimplesResponseDto> getRelatorioSimples() throws Exception {
        List<RelatorioSimplesProjection> projection = itemPedidoRepo.getRelatorioSimples();

        return projection.stream().map(itemPedidoMapper::toRelatorioResponse).toList();
    }

    public ItemPedidoResponseDto put(ItemPedidoRequestDto itemPedidoRequestDto, Long id) throws Exception {
        ItemPedido itemPedido = itemPedidoRepo.findById(id).orElseThrow(() -> new RuntimeException("ItemPedido não encontrado"));

        if (itemPedidoRequestDto.nome() != null) {
            itemPedido.setNome(itemPedidoRequestDto.nome());
        }

        if (itemPedidoRequestDto.preco() != null) {
            itemPedido.setPreco(itemPedidoRequestDto.preco());
        }

        Pedido pedido = pedidoRepo.findById(itemPedido.getPedido().getId()).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if (itemPedidoRequestDto.pedidoId() != null) {
            itemPedido.setPedido(pedido);
        }

        PedidoResponseDto pedidoResponseDto = pedidoMapper.toResponse(pedidoRepo.findById(itemPedido.getPedido().getId()).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));

        return itemPedidoMapper.toResponse(itemPedido);
    }

    public void delete(Long id) throws Exception {
        itemPedidoRepo.deleteById(id);
    }

}

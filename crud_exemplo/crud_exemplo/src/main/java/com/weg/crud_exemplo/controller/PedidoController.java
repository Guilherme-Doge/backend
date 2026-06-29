package com.weg.crud_exemplo.controller;

import com.weg.crud_exemplo.dto.ItemPedidoResponseDto;
import com.weg.crud_exemplo.dto.PedidoRequestDto;
import com.weg.crud_exemplo.dto.PedidoResponseDto;
import com.weg.crud_exemplo.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public PedidoResponseDto post(@RequestBody PedidoRequestDto pedidoRequestDto) {
        try {
            return pedidoService.post(pedidoRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public PedidoResponseDto get(@PathVariable Long id) {
        try {
            return pedidoService.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<PedidoResponseDto> getAll() {
        try {
            return pedidoService.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public PedidoResponseDto put(@RequestBody PedidoRequestDto pedidoRequestDto, @PathVariable Long id) {
        try {
            return pedidoService.put(pedidoRequestDto, id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            pedidoService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}

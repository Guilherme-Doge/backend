package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.PedidoRequestDto;
import com.weg.jpa_relacionamentos.dto.PedidoResponseDto;
import com.weg.jpa_relacionamentos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public PedidoResponseDto post(@RequestBody PedidoRequestDto dto) {
        return service.post(dto);
    }

    @GetMapping
    public List<PedidoResponseDto> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public PedidoResponseDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public PedidoResponseDto put(@PathVariable Long id, @RequestBody PedidoRequestDto dto) {
        return service.put(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

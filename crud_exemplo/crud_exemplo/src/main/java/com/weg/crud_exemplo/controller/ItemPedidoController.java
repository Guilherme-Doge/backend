package com.weg.crud_exemplo.controller;

import com.weg.crud_exemplo.dto.ItemPedidoRequestDto;
import com.weg.crud_exemplo.dto.ItemPedidoResponseDto;
import com.weg.crud_exemplo.dto.RelatorioSimplesResponseDto;
import com.weg.crud_exemplo.service.ItemPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemPedido")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @PostMapping
    public ItemPedidoResponseDto post(@RequestBody ItemPedidoRequestDto itemPedidoRequestDto) {
        try {
            return itemPedidoService.post(itemPedidoRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ItemPedidoResponseDto get(@PathVariable Long id) {
        try {
            return itemPedidoService.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<ItemPedidoResponseDto> getAll() {
        try {
            return itemPedidoService.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/relatorio")
    public ResponseEntity<List<RelatorioSimplesResponseDto>> getRelatorioSimples() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(itemPedidoService.getRelatorioSimples());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ItemPedidoResponseDto put(@RequestBody ItemPedidoRequestDto itemPedidoRequestDto, @PathVariable Long id) {
        try {
            return itemPedidoService.put(itemPedidoRequestDto, id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            itemPedidoService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

}

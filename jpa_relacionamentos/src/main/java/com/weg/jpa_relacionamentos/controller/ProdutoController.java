package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.ProdutoRequestDto;
import com.weg.jpa_relacionamentos.dto.ProdutoResponseDto;
import com.weg.jpa_relacionamentos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService service;

    @PostMapping
    public ProdutoResponseDto post(@RequestBody ProdutoRequestDto dto) { return service.post(dto); }

    @GetMapping
    public List<ProdutoResponseDto> list() { return service.list(); }

    @GetMapping("/{id}")
    public ProdutoResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public ProdutoResponseDto put(@PathVariable Long id, @RequestBody ProdutoRequestDto dto) { return service.put(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

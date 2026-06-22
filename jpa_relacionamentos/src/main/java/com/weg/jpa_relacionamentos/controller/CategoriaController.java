package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.CategoriaRequestDto;
import com.weg.jpa_relacionamentos.dto.CategoriaResponseDto;
import com.weg.jpa_relacionamentos.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService service;

    @PostMapping
    public CategoriaResponseDto post(@RequestBody CategoriaRequestDto dto) { return service.post(dto); }

    @GetMapping
    public List<CategoriaResponseDto> list() { return service.list(); }

    @GetMapping("/{id}")
    public CategoriaResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public CategoriaResponseDto put(@PathVariable Long id, @RequestBody CategoriaRequestDto dto) { return service.put(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

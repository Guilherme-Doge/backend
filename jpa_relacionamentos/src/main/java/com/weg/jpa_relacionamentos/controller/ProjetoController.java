package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.ProjetoRequestDto;
import com.weg.jpa_relacionamentos.dto.ProjetoResponseDto;
import com.weg.jpa_relacionamentos.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
@RequiredArgsConstructor
public class ProjetoController {
    private final ProjetoService service;

    @PostMapping
    public ProjetoResponseDto post(@RequestBody ProjetoRequestDto dto) { return service.post(dto); }

    @GetMapping
    public List<ProjetoResponseDto> list() { return service.list(); }

    @GetMapping("/{id}")
    public ProjetoResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public ProjetoResponseDto put(@PathVariable Long id, @RequestBody ProjetoRequestDto dto) { return service.put(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

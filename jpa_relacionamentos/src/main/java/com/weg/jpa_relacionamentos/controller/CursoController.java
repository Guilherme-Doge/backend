package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.CursoRequestDto;
import com.weg.jpa_relacionamentos.dto.CursoResponseDto;
import com.weg.jpa_relacionamentos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService service;

    @PostMapping
    public CursoResponseDto post(@RequestBody CursoRequestDto dto) { return service.post(dto); }

    @GetMapping
    public List<CursoResponseDto> list() { return service.list(); }

    @GetMapping("/{id}")
    public CursoResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public CursoResponseDto put(@PathVariable Long id, @RequestBody CursoRequestDto dto) { return service.put(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

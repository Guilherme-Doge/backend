package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.TarefaRequestDto;
import com.weg.jpa_relacionamentos.dto.TarefaResponseDto;
import com.weg.jpa_relacionamentos.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService service;

    @PostMapping
    public TarefaResponseDto post(@RequestBody TarefaRequestDto dto) { return service.post(dto); }

    @GetMapping
    public List<TarefaResponseDto> list() { return service.list(); }

    @GetMapping("/{id}")
    public TarefaResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public TarefaResponseDto put(@PathVariable Long id, @RequestBody TarefaRequestDto dto) { return service.put(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

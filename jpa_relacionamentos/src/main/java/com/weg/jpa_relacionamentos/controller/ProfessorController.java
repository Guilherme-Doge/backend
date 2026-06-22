package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.ProfessorRequestDto;
import com.weg.jpa_relacionamentos.dto.ProfessorResponseDto;
import com.weg.jpa_relacionamentos.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService service;

    @PostMapping
    public ProfessorResponseDto post(@RequestBody ProfessorRequestDto dto) { return service.post(dto); }

    @GetMapping
    public List<ProfessorResponseDto> list() { return service.list(); }

    @GetMapping("/{id}")
    public ProfessorResponseDto get(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public ProfessorResponseDto put(@PathVariable Long id, @RequestBody ProfessorRequestDto dto) { return service.put(id, dto); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}

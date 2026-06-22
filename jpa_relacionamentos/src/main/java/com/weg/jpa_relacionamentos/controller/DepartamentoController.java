package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.DepartamentoRequestDto;
import com.weg.jpa_relacionamentos.dto.DepartamentoResponseDto;
import com.weg.jpa_relacionamentos.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
@RequiredArgsConstructor
public class DepartamentoController {

    private final DepartamentoService service;

    @PostMapping()
    public DepartamentoResponseDto postDepartamento(@RequestBody DepartamentoRequestDto departamentoRequestDto) { return service.post(departamentoRequestDto); }

    @GetMapping
    public List<DepartamentoResponseDto> getDepartamentos() { return service.list(); }

    @GetMapping("/{id}")
    public DepartamentoResponseDto getDepartamento(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public DepartamentoResponseDto putDepartamento(@PathVariable Long id, @RequestBody DepartamentoRequestDto departamentoRequestDto) { return service.put(id, departamentoRequestDto); }

    @DeleteMapping("/{id}")
    public void deleteDepartamento(@PathVariable Long id) { service.delete(id); }
}

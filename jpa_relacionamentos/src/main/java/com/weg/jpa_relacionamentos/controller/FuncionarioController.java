package com.weg.jpa_relacionamentos.controller;

import com.weg.jpa_relacionamentos.dto.FuncionarioRequestDto;
import com.weg.jpa_relacionamentos.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService service;

    @PostMapping()
    public FuncionarioResponseDto postFuncionario(@RequestBody FuncionarioRequestDto funcionarioRequestDto) { return service.post(funcionarioRequestDto); }

    @GetMapping
    public List<FuncionarioResponseDto> getFuncionarios() { return service.list(); }

    @GetMapping("/{id}")
    public FuncionarioResponseDto getFuncionario(@PathVariable Long id) { return service.get(id); }

    @PutMapping("/{id}")
    public FuncionarioResponseDto putFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequestDto funcionarioRequestDto) { return service.put(id, funcionarioRequestDto); }

    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable Long id) { service.delete(id); }
}

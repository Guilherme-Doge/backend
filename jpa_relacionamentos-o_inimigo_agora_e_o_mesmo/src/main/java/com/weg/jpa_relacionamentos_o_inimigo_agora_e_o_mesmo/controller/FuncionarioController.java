package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.FuncionarioRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.FuncionarioResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public FuncionarioResponseDto create(@RequestBody FuncionarioRequestDto funcionarioRequestDto) {
        return funcionarioService.create(funcionarioRequestDto);
    }

    @GetMapping("/{id}")
    public FuncionarioResponseDto getById(@PathVariable Long id) {
        return funcionarioService.getById(id);
    }
}

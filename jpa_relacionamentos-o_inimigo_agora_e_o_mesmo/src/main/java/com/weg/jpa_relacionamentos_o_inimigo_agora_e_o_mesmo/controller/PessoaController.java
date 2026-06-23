package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PessoaRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PessoaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public PessoaResponseDto create(@RequestBody PessoaRequestDto pessoaRequestDto) {
        return pessoaService.create(pessoaRequestDto);
    }

    @GetMapping("/{id}")
    public PessoaResponseDto getById(@PathVariable Long id) {
        return pessoaService.getById(id);
    }
}

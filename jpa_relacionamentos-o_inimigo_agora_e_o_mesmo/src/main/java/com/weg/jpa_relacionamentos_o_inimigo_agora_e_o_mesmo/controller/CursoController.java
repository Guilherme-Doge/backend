package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public CursoResponseDto create(@RequestBody CursoRequestDto cursoRequestDto) {
        return cursoService.create(cursoRequestDto);
    }

    @GetMapping("/{id}")
    public CursoResponseDto getById(@PathVariable Long id) {
        return cursoService.getById(id);
    }
}

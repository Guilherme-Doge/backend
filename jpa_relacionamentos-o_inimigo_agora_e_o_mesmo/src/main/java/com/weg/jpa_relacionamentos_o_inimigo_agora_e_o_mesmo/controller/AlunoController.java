package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.AlunoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public AlunoResponseDto create(@RequestBody AlunoRequestDto alunoRequestDto) {
        return alunoService.create(alunoRequestDto);
    }

    @PostMapping("/{alunoId}/curso/{cursoId}")
    public AlunoResponseDto matricular(@PathVariable Long alunoId, @PathVariable Long cursoId) {
        return alunoService.matricular(alunoId, cursoId);
    }

    @GetMapping("/{id}")
    public AlunoResponseDto getById(@PathVariable Long id) {
        return alunoService.getById(id);
    }
}

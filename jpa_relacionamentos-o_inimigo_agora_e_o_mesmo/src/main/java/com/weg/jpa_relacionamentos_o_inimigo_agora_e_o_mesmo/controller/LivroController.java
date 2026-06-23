package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livro")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public LivroResponseDto create(@RequestBody LivroRequestDto livroRequestDto) {
        return livroService.create(livroRequestDto);
    }
}

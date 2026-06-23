package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.MusicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musica")
@RequiredArgsConstructor
public class MusicaController {

    private final MusicaService musicaService;

    @PostMapping
    public MusicaResponseDto create(@RequestBody MusicaRequestDto musicaRequestDto) {
        return musicaService.create(musicaRequestDto);
    }
}

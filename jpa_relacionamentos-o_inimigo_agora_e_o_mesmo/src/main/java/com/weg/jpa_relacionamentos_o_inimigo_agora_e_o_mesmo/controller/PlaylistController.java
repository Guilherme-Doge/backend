package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.controller;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PlaylistRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PlaylistResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping
    public PlaylistResponseDto create(@RequestBody PlaylistRequestDto playlistRequestDto) {
        return playlistService.create(playlistRequestDto);
    }

    @PostMapping("/{playlistId}/musica/{musicaId}")
    public PlaylistResponseDto adicionarMusica(@PathVariable Long playlistId, @PathVariable Long musicaId) {
        return playlistService.adicionarMusica(playlistId, musicaId);
    }

    @GetMapping("/{id}")
    public PlaylistResponseDto getById(@PathVariable Long id) {
        return playlistService.getById(id);
    }
}

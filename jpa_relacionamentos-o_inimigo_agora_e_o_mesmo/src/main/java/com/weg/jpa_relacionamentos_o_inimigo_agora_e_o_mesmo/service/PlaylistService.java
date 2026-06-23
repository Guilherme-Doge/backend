package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PlaylistRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PlaylistResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.PlaylistMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Musica;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Playlist;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.MusicaRepo;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.PlaylistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepo playlistRepo;
    private final MusicaRepo musicaRepo;
    private final PlaylistMapper playlistMapper;

    public PlaylistResponseDto create(PlaylistRequestDto playlistRequestDto) {
        Playlist playlist = playlistMapper.toEntity(playlistRequestDto);
        playlistRepo.save(playlist);
        return playlistMapper.toResponse(playlist);
    }

    public PlaylistResponseDto adicionarMusica(Long playlistId, Long musicaId) {
        Playlist playlist = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada!"));

        Musica musica = musicaRepo.findById(musicaId)
                .orElseThrow(() -> new RuntimeException("Música não encontrada!"));

        for (Musica musicaExistente : playlist.getMusicas()) {
            if (musicaExistente.getId().equals(musicaId)) {
                throw new RuntimeException("Música já existe na playlist!");
            }
        }

        playlist.getMusicas().add(musica);
        playlistRepo.save(playlist);

        return playlistMapper.toResponse(playlist);
    }

    public PlaylistResponseDto getById(Long id) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada!"));

        return playlistMapper.toResponse(playlist);
    }
}

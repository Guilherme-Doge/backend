package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.MusicaMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Musica;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.MusicaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicaService {

    private final MusicaRepo musicaRepo;
    private final MusicaMapper musicaMapper;

    public MusicaResponseDto create(MusicaRequestDto musicaRequestDto) {
        Musica musica = musicaMapper.toEntity(musicaRequestDto);
        musicaRepo.save(musica);
        return musicaMapper.toResponse(musica);
    }
}

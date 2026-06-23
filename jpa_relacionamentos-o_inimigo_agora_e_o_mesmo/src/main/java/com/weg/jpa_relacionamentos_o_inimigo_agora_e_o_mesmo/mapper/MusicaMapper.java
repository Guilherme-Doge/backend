package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Musica;
import org.springframework.stereotype.Component;

@Component
public class MusicaMapper {

    public Musica toEntity(MusicaRequestDto musicaRequestDto) {
        return new Musica(musicaRequestDto.nome(), musicaRequestDto.artista());
    }

    public MusicaResponseDto toResponse(Musica musica) {
        return new MusicaResponseDto(musica.getId(), musica.getNome(), musica.getArtista());
    }
}

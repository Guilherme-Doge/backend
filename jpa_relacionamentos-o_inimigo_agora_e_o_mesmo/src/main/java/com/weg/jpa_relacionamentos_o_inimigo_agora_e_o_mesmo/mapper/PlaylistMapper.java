package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.MusicaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PlaylistRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PlaylistResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Musica;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Playlist;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaylistMapper {

    public Playlist toEntity(PlaylistRequestDto playlistRequestDto) {
        return new Playlist(playlistRequestDto.nome());
    }

    public PlaylistResponseDto toResponse(Playlist playlist) {
        List<MusicaResponseDto> musicas = new ArrayList<>();
        for (Musica musica : playlist.getMusicas()) {
            musicas.add(new MusicaResponseDto(musica.getId(), musica.getNome(), musica.getArtista()));
        }
        return new PlaylistResponseDto(playlist.getId(), playlist.getNome(), musicas);
    }
}

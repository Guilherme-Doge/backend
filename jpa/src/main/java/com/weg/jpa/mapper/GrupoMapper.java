package com.weg.jpa.mapper;

import com.weg.jpa.dto.GrupoRequestDto;
import com.weg.jpa.dto.GrupoResponseDto;
import com.weg.jpa.model.Grupo;

public class GrupoMapper {
    public Grupo toEntity(GrupoRequestDto grupoRequestDto) {
        return new Grupo(
                grupoRequestDto.nome(),
                grupoRequestDto.conversa(),
                grupoRequestDto.membros()
        );
    }

    public GrupoResponseDto toResponse(Grupo grupo) {
        return new GrupoResponseDto(
                grupo.getId(),
                grupo.getNome(),
                grupo.getConversa(),
                grupo.getMembros()
        );
    }
}

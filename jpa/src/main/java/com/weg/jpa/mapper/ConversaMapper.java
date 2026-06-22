package com.weg.jpa.mapper;

import com.weg.jpa.dto.ConversaRequestDto;
import com.weg.jpa.dto.ConversaResponseDto;
import com.weg.jpa.model.Conversa;
import org.springframework.stereotype.Component;

@Component
public class ConversaMapper {
    public Conversa toEntity(ConversaRequestDto conversaRequestDto) {
        return new Conversa(
                conversaRequestDto.mensagens(),
                conversaRequestDto.grupo()
        );
    }

    public ConversaResponseDto toResponse(Conversa conversa) {
        return new ConversaResponseDto(
                conversa.getId(),
                conversa.getMensagens(),
                conversa.getGrupo()
        );
    }
}

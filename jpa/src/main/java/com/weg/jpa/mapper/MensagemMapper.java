package com.weg.jpa.mapper;

import com.weg.jpa.dto.MensagemRequestDto;
import com.weg.jpa.dto.MensagemResponseDto;
import com.weg.jpa.model.Mensagem;

public class MensagemMapper {
    public Mensagem toEntity(MensagemRequestDto mensagemRequestDto) {
        return new Mensagem(
                mensagemRequestDto.texto(),
                mensagemRequestDto.dataEnvio(),
                mensagemRequestDto.conversa(),
                mensagemRequestDto.remetente()
        );
    }

    public MensagemResponseDto toResponse(Mensagem mensagem) {
        return new MensagemResponseDto(
                mensagem.getId(),
                mensagem.getTexto(),
                mensagem.getDataEnvio(),
                mensagem.getConversa(),
                mensagem.getRemetente()
        );
    }
}

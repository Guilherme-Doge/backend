package com.weg.jpa.mapper;

import com.weg.jpa.dto.LigacaoRequestDto;
import com.weg.jpa.dto.LigacaoResponseDto;
import com.weg.jpa.model.Ligacao;
import org.springframework.stereotype.Component;

@Component
public class LigacaoMapper {
    public Ligacao toEntity(LigacaoRequestDto ligacaoRequestDto) {
        return new Ligacao(
            ligacaoRequestDto.dataHora(),
            ligacaoRequestDto.conta(),
            ligacaoRequestDto.contato()
        );
    }

    public LigacaoResponseDto toResponse(Ligacao ligacao) {
        return new LigacaoResponseDto(
                ligacao.getId(),
                ligacao.getDataHora(),
                ligacao.getConta(),
                ligacao.getContato()
        );
    }
}

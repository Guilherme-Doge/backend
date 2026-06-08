package com.weg.jpa.mapper;

import com.weg.jpa.dto.ContatoRequestDto;
import com.weg.jpa.dto.ContatoResponseDto;
import com.weg.jpa.model.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    public Contato toEntity(ContatoRequestDto dto) {
        return new Contato(
                dto.nome(),
                dto.numero());
    }

    public ContatoResponseDto toResponse(Contato contato) {
        return new ContatoResponseDto(
                contato.getId(),
                contato.getNome(),
                contato.getNumero()
        );
    }
}

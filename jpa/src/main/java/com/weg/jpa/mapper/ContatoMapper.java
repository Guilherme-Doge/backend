package com.weg.jpa.mapper;

import com.weg.jpa.dto.ContatoRequestDto;
import com.weg.jpa.dto.ContatoResponseDto;
import com.weg.jpa.model.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    public Contato toEntity(ContatoRequestDto contatoRequestDto) {
        return new Contato(
                contatoRequestDto.nome(),
                contatoRequestDto.numero(),
                contatoRequestDto.conta()
        );
    }

    public ContatoResponseDto toResponse(Contato contato) {
        return new ContatoResponseDto(
                contato.getId(),
                contato.getNome(),
                contato.getNumero(),
                contato.getConta()
        );
    }
}

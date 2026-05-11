package com.senai.minha_primeira_api.mapper;

import com.senai.minha_primeira_api.dto.ContatoRequestDto;
import com.senai.minha_primeira_api.dto.ContatoResponseDto;
import com.senai.minha_primeira_api.model.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {
    public Contato paraEntidade(
            ContatoRequestDto requisicaoDto
    ){
        return new Contato(
                requisicaoDto.nome(),
                requisicaoDto.numero()
        );
    }

    public ContatoResponseDto paraRespostaDto(
            Contato contato
    ){
        return new ContatoResponseDto(
                contato.getId(),
                contato.getNome(),
                contato.getNumero()
        );
    }
}

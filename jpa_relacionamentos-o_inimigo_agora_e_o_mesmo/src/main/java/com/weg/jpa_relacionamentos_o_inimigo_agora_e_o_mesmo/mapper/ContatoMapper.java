package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.ContatoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {

    public ContatoResponseDto toResponse(Contato contato) {
        return new ContatoResponseDto(contato.getId(), contato.getEmail(), contato.getTelefone());
    }
}

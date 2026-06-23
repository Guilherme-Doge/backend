package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.EnderecoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public EnderecoResponseDto toResponse(Endereco endereco) {
        return new EnderecoResponseDto(
                endereco.getId(),
                endereco.getRua(),
                endereco.getNumero()
        );
    }
}

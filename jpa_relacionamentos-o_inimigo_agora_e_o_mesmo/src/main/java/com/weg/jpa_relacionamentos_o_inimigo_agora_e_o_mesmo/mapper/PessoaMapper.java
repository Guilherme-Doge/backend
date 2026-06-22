package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PessoaRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.PessoaResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {
    public PessoaResponseDto toResponse(Pessoa pessoa) {
        return new PessoaResponseDto(pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDocumento().getId());
    }

    public Pessoa toEntity(PessoaRequestDto pessoaRequestDto) {
        return new Pessoa(pessoaRequestDto.nome(),
                pessoaRequestDto.documento());
    }
}

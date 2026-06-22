package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.DocumentoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.DocumentoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Documento;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {
    public DocumentoResponseDto toResponse(Documento documento) {
        return new DocumentoResponseDto(documento.getId(),
                documento.getNumero(),
                documento.getPessoa().getId());
    }

    public Documento toEntity(DocumentoRequestDto documentoRequestDto) {
        return new Documento(documentoRequestDto.numero(),
                new Pessoa(documentoRequestDto.pessoa_id()));
    }
}

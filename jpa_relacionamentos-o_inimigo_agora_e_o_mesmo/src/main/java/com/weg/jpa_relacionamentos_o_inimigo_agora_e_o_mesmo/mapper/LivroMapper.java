package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequestDto livroRequestDto) {
        return new Livro(livroRequestDto.titulo(), livroRequestDto.autor());
    }

    public LivroResponseDto toResponse(Livro livro) {
        return new LivroResponseDto(livro.getId(), livro.getTitulo(), livro.getAutor());
    }
}

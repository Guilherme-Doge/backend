package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.LivroResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.LivroMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Livro;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.LivroRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepo livroRepo;
    private final LivroMapper livroMapper;

    public LivroResponseDto create(LivroRequestDto livroRequestDto) {
        Livro livro = livroMapper.toEntity(livroRequestDto);
        livroRepo.save(livro);
        return livroMapper.toResponse(livro);
    }
}

package com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.service;

import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoRequestDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.dto.CursoResponseDto;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.mapper.CursoMapper;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.model.Curso;
import com.weg.jpa_relacionamentos_o_inimigo_agora_e_o_mesmo.repo.CursoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepo cursoRepo;
    private final CursoMapper cursoMapper;

    public CursoResponseDto create(CursoRequestDto cursoRequestDto) {
        Curso curso = cursoMapper.toEntity(cursoRequestDto);

        if (cursoRepo.existsByNome(curso.getNome())) {
            throw new RuntimeException("Curso já cadastrado!");
        }

        cursoRepo.save(curso);
        return cursoMapper.toResponse(curso);
    }

    public CursoResponseDto getById(Long id) {
        Curso curso = cursoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado!"));

        return cursoMapper.toResponse(curso);
    }
}

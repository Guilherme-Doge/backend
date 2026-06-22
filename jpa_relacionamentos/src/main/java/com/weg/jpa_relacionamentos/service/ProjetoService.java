package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.ProjetoRequestDto;
import com.weg.jpa_relacionamentos.dto.ProjetoResponseDto;
import com.weg.jpa_relacionamentos.mapper.ProjetoMapper;
import com.weg.jpa_relacionamentos.model.Projeto;
import com.weg.jpa_relacionamentos.repo.ProjetoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetoService {
    private final ProjetoMapper projetoMapper;
    private final ProjetoRepo projetoRepo;

    public ProjetoResponseDto post(ProjetoRequestDto dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new RuntimeException("Informe o nome do projeto");
        }
        return projetoMapper.toResponse(projetoRepo.save(projetoMapper.toEntity(dto)));
    }

    public ProjetoResponseDto get(Long id) {
        Projeto projeto = projetoRepo.findById(id).orElseThrow(() -> new RuntimeException("Projeto não existe"));
        return projetoMapper.toResponse(projeto);
    }

    public List<ProjetoResponseDto> list() {
        return projetoRepo.findAll().stream().map(projetoMapper::toResponse).toList();
    }

    public ProjetoResponseDto put(Long id, ProjetoRequestDto dto) {
        Projeto projeto = projetoRepo.findById(id).orElseThrow(() -> new RuntimeException("Projeto não existe"));
        if (dto.nome() != null && !dto.nome().isBlank()) {
            projeto.setNome(dto.nome());
        }
        return projetoMapper.toResponse(projetoRepo.save(projeto));
    }

    public void delete(Long id) {
        if (!projetoRepo.existsById(id)) {
            throw new RuntimeException("Projeto não existe");
        }
        projetoRepo.deleteById(id);
    }
}

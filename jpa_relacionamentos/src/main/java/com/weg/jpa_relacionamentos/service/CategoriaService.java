package com.weg.jpa_relacionamentos.service;

import com.weg.jpa_relacionamentos.dto.CategoriaRequestDto;
import com.weg.jpa_relacionamentos.dto.CategoriaResponseDto;
import com.weg.jpa_relacionamentos.mapper.CategoriaMapper;
import com.weg.jpa_relacionamentos.model.Categoria;
import com.weg.jpa_relacionamentos.repo.CategoriaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaMapper categoriaMapper;
    private final CategoriaRepo categoriaRepo;

    @Transactional
    public CategoriaResponseDto post(CategoriaRequestDto dto) {
        return categoriaMapper.toResponse(categoriaRepo.save(categoriaMapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDto get(Long id) {
        Categoria categoria = categoriaRepo.findById(id).orElseThrow(() -> new RuntimeException("Categoria não existe"));
        return categoriaMapper.toResponse(categoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDto> list() {
        return categoriaRepo.findAll().stream().map(categoriaMapper::toResponse).toList();
    }

    @Transactional
    public CategoriaResponseDto put(Long id, CategoriaRequestDto dto) {
        Categoria categoria = categoriaRepo.findById(id).orElseThrow(() -> new RuntimeException("Categoria não existe"));
        if (dto.nome() != null && !dto.nome().isBlank()) {
            categoria.setNome(dto.nome());
        }
        return categoriaMapper.toResponse(categoriaRepo.save(categoria));
    }

    @Transactional
    public void delete(Long id) {
        if (!categoriaRepo.existsById(id)) {
            throw new RuntimeException("Categoria não existe");
        }
        categoriaRepo.deleteById(id);
    }
}

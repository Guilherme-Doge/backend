package com.weg.jpa_relacionamentos.mapper;

import com.weg.jpa_relacionamentos.dto.CategoriaRequestDto;
import com.weg.jpa_relacionamentos.dto.CategoriaResponseDto;
import com.weg.jpa_relacionamentos.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    public Categoria toEntity(CategoriaRequestDto dto) {
        return new Categoria(dto.nome());
    }

    public CategoriaResponseDto toResponse(Categoria categoria) {
        return new CategoriaResponseDto(categoria.getId(), categoria.getNome());
    }
}

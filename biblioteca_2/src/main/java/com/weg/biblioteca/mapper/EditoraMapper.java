package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.EditoraRequestDto;
import com.weg.biblioteca.dto.EditoraResponseDto;
import com.weg.biblioteca.model.Editora;
import org.springframework.stereotype.Component;

@Component
public class EditoraMapper {
    public Editora toEntity(EditoraRequestDto editoraRequestDto) {
        return new Editora(editoraRequestDto.nome());
    }

    public EditoraResponseDto toResponse(Editora editora) {
        return new EditoraResponseDto(editora.getId(),
                editora.getNome());
    }
}

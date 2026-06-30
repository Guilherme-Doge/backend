package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.AutorRequestDto;
import com.weg.biblioteca.dto.AutorResponseDto;
import com.weg.biblioteca.model.Autor;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper {
    public Autor toEntity(AutorRequestDto autorRequestDto) {
        return new Autor(autorRequestDto.nome(),
                        autorRequestDto.nacionalidade());
    }

    public AutorResponseDto toResponse(Autor autor) {
        return new AutorResponseDto(autor.getId(),
                                    autor.getNome(),
                                    autor.getNacionalidade());
    }
}

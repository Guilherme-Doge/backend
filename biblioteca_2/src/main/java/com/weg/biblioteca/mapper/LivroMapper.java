package com.weg.biblioteca.mapper;

import com.weg.biblioteca.dto.*;
import com.weg.biblioteca.model.Autor;
import com.weg.biblioteca.model.Editora;
import com.weg.biblioteca.model.Livro;
import com.weg.biblioteca.projection.LivroMinimoProjection;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {
    public Livro toEntity(LivroRequestDto livroRequestDto, Editora editora, List<Autor> autores) {
        return new Livro(livroRequestDto.titulo(),
                        livroRequestDto.isbn(),
                        livroRequestDto.preco(),
                        livroRequestDto.dataPublicacao(),
                        livroRequestDto.categoria(),
                        editora,
                        autores);
    }

    public LivroResponseDto toResponse(Livro livro, EditoraResponseDto editoraResponseDto, List<AutorResponseDto> autoresResponseDto) {
        return new LivroResponseDto(livro.getId(),
                livro.getTitulo(),
                livro.getIsbn(),
                livro.getPreco(),
                livro.getDataPublicacao(),
                livro.getCategoria(),
                editoraResponseDto,
                autoresResponseDto);
    }

    public LivroMinimoProjection toLivroMinimo(String titulo, Double preco) {
        return new LivroMinimoProjection(titulo, preco);
    }
}
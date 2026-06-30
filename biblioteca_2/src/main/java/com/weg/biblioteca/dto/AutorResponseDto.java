package com.weg.biblioteca.dto;

import com.weg.biblioteca.model.Livro;

import java.util.List;

public record AutorResponseDto(
        Long id,
        String nome,
        String nacionalidade
) {
}

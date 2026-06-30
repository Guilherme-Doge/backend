package com.weg.biblioteca.dto;

import java.time.LocalDate;
import java.util.List;

public record LivroResponseDto(
        Long id,
        String titulo,
        String isbn,
        Double preco,
        LocalDate dataPublicacao,
        String categoria,
        EditoraResponseDto editora,
        List<AutorResponseDto> autores
) {
}

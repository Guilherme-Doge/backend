package com.weg.biblioteca.dto;

import java.time.LocalDate;
import java.util.List;

public record LivroRequestDto(
        String titulo,
        String isbn,
        Double preco,
        LocalDate dataPublicacao,
        String categoria,
        Long editoraId,
        List<Long> autoresId
) {
}

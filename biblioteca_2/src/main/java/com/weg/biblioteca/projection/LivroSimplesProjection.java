package com.weg.biblioteca.projection;

import java.time.LocalDate;

public record LivroSimplesProjection(
    String tituloLivro,
    String isbnLivro,
    Double precoLivro,
    LocalDate dataPublicacaoLivro,
    String categoriaLivro
) {
}
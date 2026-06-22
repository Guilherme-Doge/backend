package com.weg.jpa_relacionamentos.dto;

import java.math.BigDecimal;

public record ProdutoResponseDto(
        Long id,
        String nome,
        BigDecimal preco,
        Long categoriaId,
        String categoriaNome
) {}
